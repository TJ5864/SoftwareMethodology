package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;
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
            Toast.makeText(this, option.getName() + " selected", Toast.LENGTH_SHORT).show();
        });

        recyclerView.setAdapter(adapter);
    }

    /** Builds the list of 8 pizza options shown in the RecyclerView. */
    private List<PizzaOption> buildPizzaList() {
        List<PizzaOption> list = new ArrayList<>();

        list.add(new PizzaOption(getString(R.string.ny_deluxe),
                "Sausage, Pepperoni, Green Pepper, Onion, Mushroom",
                R.drawable.deluxe_pizza));

        list.add(new PizzaOption(getString(R.string.ny_bbq_chicken),
                "BBQ Chicken, Green Pepper, Provolone, Cheddar",
                R.drawable.bbq_pizza));

        list.add(new PizzaOption(getString(R.string.ny_meatzza),
                "Sausage, Ham, Pepperoni, Beef",
                R.drawable.meat_pizza));

        list.add(new PizzaOption(getString(R.string.ny_byo),
                "Pick up to 5 toppings",
                R.drawable.pizza));

        list.add(new PizzaOption(getString(R.string.chicago_deluxe),
                "Sausage, Pepperoni, Green Pepper, Onion, Mushroom",
                R.drawable.deluxe_pizza));

        list.add(new PizzaOption(getString(R.string.chicago_bbq_chicken),
                "BBQ Chicken, Green Pepper, Provolone, Cheddar",
                R.drawable.bbq_pizza));

        list.add(new PizzaOption(getString(R.string.chicago_meatzza),
                "Sausage, Ham, Pepperoni, Beef",
                R.drawable.meat_pizza));

        list.add(new PizzaOption(getString(R.string.chicago_byo),
                "Pick up to 5 toppings",
                R.drawable.pizza));

        return list;
    }

    /** Handles the back arrow — returns to home screen. */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
