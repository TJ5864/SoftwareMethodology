package com.example.myapplication;

import java.util.ArrayList;

/** Represents a single customer order containing a list of pizzas. */
public class Order {
    private int number;
    private ArrayList<Pizza> pizzas;

    /** Constructor for Order, initializes the pizza list.
     * @param number the unique order number */
    public Order(int number) {
        this.number = number;
        pizzas = new ArrayList<>();
    }

    /** Returns the order number.
     * @return order number */
    public int getNumber() {
        return number;
    }

    /** Returns the list of pizzas in this order.
     * @return ArrayList of Pizza */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /** Adds a pizza to the order.
     * @param pizza the Pizza to add */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /** Removes a pizza from the order.
     * @param pizza the Pizza to remove */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /** Calculates and returns the total price of all pizzas in the order.
     * @return total price as a double */
    public double getTotal() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.price();
        }
        return total;
    }
}
