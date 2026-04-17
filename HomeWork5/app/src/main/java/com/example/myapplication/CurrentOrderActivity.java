package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

/** Shows the current open order, lets the user remove pizzas or place the order. */
public class CurrentOrderActivity extends AppCompatActivity {

    private Order currentOrder;
    private ArrayAdapter<String> adapter;
    private List<String> pizzaLabels;
    private ListView pizzaListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.current_order_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        currentOrder = StoreManager.getInstance().getCurrentOrder();
        pizzaListView = findViewById(R.id.pizzaListView);
        pizzaLabels   = new ArrayList<>();

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_activated_1, pizzaLabels);
        pizzaListView.setAdapter(adapter);

        refreshList();

        Button removeBtn = findViewById(R.id.btnRemoveSelected);
        removeBtn.setOnClickListener(v -> removeSelected());

        Button placeBtn = findViewById(R.id.btnPlaceOrder);
        placeBtn.setOnClickListener(v -> confirmPlaceOrder());
    }

    /** Refreshes list and price footer whenever the screen becomes visible. */
    @Override
    protected void onResume() {
        super.onResume();
        currentOrder = StoreManager.getInstance().getCurrentOrder();
        refreshList();
    }

    private void refreshList() {
        pizzaLabels.clear();
        for (Pizza p : currentOrder.getPizzas()) {
            pizzaLabels.add(formatPizza(p));
        }
        adapter.notifyDataSetChanged();

        TextView orderNumberLabel = findViewById(R.id.orderNumberLabel);
        orderNumberLabel.setText(getString(R.string.order_number_prefix) + currentOrder.getNumber());

        updatePriceFooter();
    }

    private void removeSelected() {
        int selected = pizzaListView.getCheckedItemPosition();
        if (selected == ListView.INVALID_POSITION) {
            Toast.makeText(this, R.string.toast_no_selection, Toast.LENGTH_SHORT).show();
            return;
        }
        Pizza toRemove = currentOrder.getPizzas().get(selected);
        currentOrder.removePizza(toRemove);
        pizzaListView.setItemChecked(selected, false);
        refreshList();
    }

    private void confirmPlaceOrder() {
        if (currentOrder.getPizzas().isEmpty()) {
            Toast.makeText(this, R.string.toast_empty_order, Toast.LENGTH_SHORT).show();
            return;
        }

        double subtotal = currentOrder.getTotal();
        double tax      = subtotal * StoreManager.getInstance().getOrderManager().getTaxRate();
        double total    = subtotal + tax;

        String message = getString(R.string.dialog_place_order_msg,
                currentOrder.getPizzas().size(),
                String.format("$%.2f", total));

        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_place_order_title)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_confirm, (dialog, which) -> placeOrder())
                .setNegativeButton(R.string.dialog_cancel, null)
                .show();
    }

    private void placeOrder() {
        int orderNum = currentOrder.getNumber();
        StoreManager.getInstance().getOrderManager().placeOrder(currentOrder);
        StoreManager.getInstance().startNewOrder();
        Toast.makeText(this,
                getString(R.string.toast_order_placed, orderNum),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    private void updatePriceFooter() {
        double subtotal = currentOrder.getTotal();
        double tax      = subtotal * StoreManager.getInstance().getOrderManager().getTaxRate();
        double total    = subtotal + tax;

        TextView subtotalView = findViewById(R.id.subtotalValue);
        TextView taxView      = findViewById(R.id.taxValue);
        TextView totalView    = findViewById(R.id.totalValue);

        subtotalView.setText(String.format("$%.2f", subtotal));
        taxView.setText(String.format("$%.2f", tax));
        totalView.setText(String.format("$%.2f", total));
    }

    /** Formats a pizza into a single readable label for the ListView row. */
    private String formatPizza(Pizza p) {
        return p.getStyle() + " " + pizzaTypeName(p)
                + "  |  " + p.getSize().name().charAt(0)
                + p.getSize().name().substring(1).toLowerCase()
                + "  |  " + String.format("$%.2f", p.price());
    }

    /** Returns a human-readable type name by inspecting the runtime class. */
    private String pizzaTypeName(Pizza p) {
        String cls = p.getClass().getSimpleName();
        switch (cls) {
            case "BBQChicken":  return "BBQ Chicken";
            case "BuildYourOwn": return "Build Your Own";
            default:            return cls;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
