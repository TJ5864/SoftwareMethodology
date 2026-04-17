package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Lets the user pick a size, customize toppings (BYO only), and add the pizza to their order. */
public class PizzaCustomizeActivity extends AppCompatActivity {

    static final String EXTRA_STYLE     = "style";
    static final String EXTRA_TYPE      = "pizzaType";
    static final String EXTRA_NAME      = "name";
    static final String EXTRA_IMAGE_RES = "imageResId";

    private Pizza pizza;
    private boolean isBYO;
    private TextView priceView;
    private final List<Topping> allToppings = Arrays.asList(Topping.values());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_customize);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.customize_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String style     = intent.getStringExtra(EXTRA_STYLE);
        String pizzaType = intent.getStringExtra(EXTRA_TYPE);
        String name      = intent.getStringExtra(EXTRA_NAME);
        int    imageRes  = intent.getIntExtra(EXTRA_IMAGE_RES, R.drawable.pizza);

        pizza = buildPizza(style, pizzaType);
        pizza.setSize(Size.SMALL);
        isBYO = "BuildYourOwn".equals(pizzaType);

        ImageView imageView = findViewById(R.id.pizzaImage);
        imageView.setImageResource(imageRes);

        TextView nameView = findViewById(R.id.pizzaName);
        nameView.setText(name);

        TextView crustView = findViewById(R.id.pizzaCrust);
        crustView.setText(formatEnum(pizza.getCrust().name()));

        priceView = findViewById(R.id.pizzaPrice);
        updatePrice();

        setupSpinner();
        setupToppingsList();

        Button addButton = findViewById(R.id.btnAddToOrder);
        addButton.setOnClickListener(v -> addToOrder(name));
    }

    private Pizza buildPizza(String style, String pizzaType) {
        PizzaFactory factory = "NY".equals(style) ? new NYPizza() : new ChicagoPizza();
        switch (pizzaType) {
            case "Deluxe":     return factory.createDeluxe();
            case "BBQChicken": return factory.createBBQChicken();
            case "Meatzza":    return factory.createMeatzza();
            default:           return factory.createBuildYourOwn();
        }
    }

    private void setupSpinner() {
        Spinner spinner = findViewById(R.id.sizeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int pos, long id) {
                Size[] sizes = {Size.SMALL, Size.MEDIUM, Size.LARGE};
                pizza.setSize(sizes[pos]);
                updatePrice();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupToppingsList() {
        ListView listView = findViewById(R.id.toppingsList);

        if (isBYO) {
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_multiple_choice, toppingNames(allToppings));
            listView.setAdapter(adapter);

            listView.setOnItemClickListener((parent, view, pos, id) -> {
                Topping topping = allToppings.get(pos);
                if (listView.isItemChecked(pos)) {
                    if (pizza.getToppings().size() >= 5) {
                        listView.setItemChecked(pos, false);
                        showMaxToppingsDialog();
                    } else {
                        pizza.addTopping(topping);
                        updatePrice();
                    }
                } else {
                    pizza.removeTopping(topping);
                    updatePrice();
                }
            });
        } else {
            List<String> names = toppingNames(new ArrayList<>(pizza.getToppings()));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, names);
            listView.setAdapter(adapter);
        }
    }

    private void showMaxToppingsDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_max_title)
                .setMessage(R.string.dialog_max_msg)
                .setPositiveButton(R.string.dialog_ok, null)
                .show();
    }

    private void addToOrder(String name) {
        StoreManager.getInstance().getCurrentOrder().addPizza(pizza);
        Toast.makeText(this,
                name + " " + getString(R.string.toast_added),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    private void updatePrice() {
        priceView.setText(String.format("$%.2f", pizza.price()));
    }

    /** Converts an enum name like "BBQ_CHICKEN" to "Bbq Chicken". */
    private String formatEnum(String enumName) {
        StringBuilder sb = new StringBuilder();
        for (String word : enumName.split("_")) {
            sb.append(Character.toUpperCase(word.charAt(0)))
              .append(word.substring(1).toLowerCase())
              .append(" ");
        }
        return sb.toString().trim();
    }

    private List<String> toppingNames(List<Topping> toppings) {
        List<String> names = new ArrayList<>();
        for (Topping t : toppings) {
            names.add(formatEnum(t.name()));
        }
        return names;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
