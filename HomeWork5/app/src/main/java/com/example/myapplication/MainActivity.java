package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/** Home screen Activity. Entry point of the RU Pizza app. */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnOrderPizza = findViewById(R.id.btnOrderPizza);
        Button btnCurrentOrder = findViewById(R.id.btnCurrentOrder);
        Button btnAllOrders = findViewById(R.id.btnAllOrders);

        btnOrderPizza.setOnClickListener(v -> {
            Intent intent = new Intent(this, PizzaMenuActivity.class);
            startActivity(intent);
        });

        btnCurrentOrder.setOnClickListener(v -> {
            Intent intent = new Intent(this, CurrentOrderActivity.class);
            startActivity(intent);
        });

        btnAllOrders.setOnClickListener(v -> {
            Intent intent = new Intent(this, AllOrdersActivity.class);
            startActivity(intent);
        });
    }
}
