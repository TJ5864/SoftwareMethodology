package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/** Shows the full list of pizza options when the user taps Order Pizza. */
public class PizzaMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.menu_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPizzas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<PizzaOption> pizzaList = buildPizzaList();

        PizzaMenuAdapter adapter = new PizzaMenuAdapter(pizzaList, option -> {
            Intent intent = new Intent(this, PizzaCustomizeActivity.class);
            intent.putExtra(PizzaCustomizeActivity.EXTRA_STYLE,     option.getStyle());
            intent.putExtra(PizzaCustomizeActivity.EXTRA_TYPE,      option.getPizzaType());
            intent.putExtra(PizzaCustomizeActivity.EXTRA_NAME,      option.getName());
            intent.putExtra(PizzaCustomizeActivity.EXTRA_IMAGE_RES, option.getImageResId());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }

    /** Builds the list of 8 pizza options shown in the RecyclerView. */
    private List<PizzaOption> buildPizzaList() {
        List<PizzaOption> list = new ArrayList<>();

        list.add(new PizzaOption(getString(R.string.ny_deluxe),
                "Sausage, Pepperoni, Green Pepper, Onion, Mushroom",
                R.drawable.deluxe_pizza, "NY", "Deluxe"));

        list.add(new PizzaOption(getString(R.string.ny_bbq_chicken),
                "BBQ Chicken, Green Pepper, Provolone, Cheddar",
                R.drawable.bbq_pizza, "NY", "BBQChicken"));

        list.add(new PizzaOption(getString(R.string.ny_meatzza),
                "Sausage, Ham, Pepperoni, Beef",
                R.drawable.meat_pizza, "NY", "Meatzza"));

        list.add(new PizzaOption(getString(R.string.ny_byo),
                "Pick up to 5 toppings",
                R.drawable.pizza, "NY", "BuildYourOwn"));

        list.add(new PizzaOption(getString(R.string.chicago_deluxe),
                "Sausage, Pepperoni, Green Pepper, Onion, Mushroom",
                R.drawable.deluxe_pizza, "Chicago", "Deluxe"));

        list.add(new PizzaOption(getString(R.string.chicago_bbq_chicken),
                "BBQ Chicken, Green Pepper, Provolone, Cheddar",
                R.drawable.bbq_pizza, "Chicago", "BBQChicken"));

        list.add(new PizzaOption(getString(R.string.chicago_meatzza),
                "Sausage, Ham, Pepperoni, Beef",
                R.drawable.meat_pizza, "Chicago", "Meatzza"));

        list.add(new PizzaOption(getString(R.string.chicago_byo),
                "Pick up to 5 toppings",
                R.drawable.pizza, "Chicago", "BuildYourOwn"));

        return list;
    }

    /** Handles the back arrow — returns to home screen. */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
