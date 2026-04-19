package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/** Shows all placed orders and allows canceling an order. */
public class AllOrdersActivity extends AppCompatActivity {

    private TextView txtAllOrders;
    private EditText txtCancelOrderNumber;
    private OrderManager orderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("All Orders");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        orderManager = StoreManager.getInstance().getOrderManager();

        txtAllOrders = findViewById(R.id.txtAllOrders);
        txtCancelOrderNumber = findViewById(R.id.txtCancelOrderNumber);

        Button btnCancelOrder = findViewById(R.id.btnCancelOrder);
        btnCancelOrder.setOnClickListener(v -> handleCancelOrder());

        refreshView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshView();
    }

    /** Cancel order by order number (same logic as controller). */
    private void handleCancelOrder() {
        String input = txtCancelOrderNumber.getText().toString().trim();
        if (input.isEmpty()) {
            showError("Please enter an order number to cancel.");
            return;
        }

        int orderNumber;
        try {
            orderNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showError("Please enter a valid order number.");
            return;
        }

        Order toCancel = null;
        for (Order order : orderManager.getOrders()) {
            if (order.getNumber() == orderNumber) {
                toCancel = order;
                break;
            }
        }

        if (toCancel == null) {
            showError("Order #" + orderNumber + " not found.");
            return;
        }

        orderManager.cancelOrder(toCancel);
        txtCancelOrderNumber.setText("");
        refreshView();
        showInfo("Order #" + orderNumber + " has been canceled.");
    }

    /** Refresh display (same behavior as controller). */
    private void refreshView() {
        txtAllOrders.setText(buildAllOrdersText());
    }

    /** Builds display text exactly like controller logic. */
    private String buildAllOrdersText() {
        if (orderManager.getOrders().isEmpty()) {
            return "No orders have been placed yet.";
        }

        StringBuilder output = new StringBuilder();

        for (Order order : orderManager.getOrders()) {
            double subtotal = order.getTotal();
            double tax = subtotal * orderManager.getTaxRate();
            double total = subtotal + tax;

            output.append("Order #").append(order.getNumber()).append("\n");

            for (Pizza pizza : order.getPizzas()) {
                output.append("  ").append(pizza.toString()).append("\n");
            }

            output.append("  Order Total: $")
                    .append(String.format("%.2f", total))
                    .append("\n\n");
        }

        return output.toString();
    }

    private void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showInfo(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Info")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}