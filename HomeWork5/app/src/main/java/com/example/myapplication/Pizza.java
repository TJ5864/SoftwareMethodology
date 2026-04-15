package com.example.myapplication;

import java.util.ArrayList;

/** Pizza class, used to create different pizzas */
public abstract class Pizza {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /** Abstract Price method, works with the price of the pizza */
    public abstract double price();

    /** Adds a topping to the pizza. Subclasses may override to enforce limits.
     * @param topping the topping to add
     * @return true if the topping was added, false otherwise */
    public boolean addTopping(Topping topping) {
        getToppings().add(topping);
        return true;
    }

    /** Removes a topping from the pizza.
     * @param topping the topping to remove
     * @return true if the topping was removed, false if it was not present */
    public boolean removeTopping(Topping topping) {
        return getToppings().remove(topping);
    }

    /** Constructor for Pizza, creates an array list of toppings */
    public Pizza() {
        toppings = new ArrayList<>();
    }

    /** Getter Method, returns the list of toppings for the pizza
     * @return the list of toppings */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /** Getter method for crust
     * @return the current crust */
    public Crust getCrust() {
        return crust;
    }

    /** Sets the value of crust
     * @param crust the type of crust we want for the pizza */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /** Getter method for Size
     * @return size of pizza small, medium or large */
    public Size getSize() {
        return size;
    }

    /** Set the size of the pizza
     * @param size the size of the pizza being ordered */
    public void setSize(Size size) {
        this.size = size;
    }

    /** Returns the pizza style (Chicago or New York) derived from the crust type.
     * @return style as a String */
    public String getStyle() {
        if (crust == Crust.DEEP_DISH || crust == Crust.PAN || crust == Crust.STUFFED) {
            return "Chicago";
        }
        return "New York";
    }

    @Override
    public String toString() {
        return getStyle() + " " + getClass().getSimpleName()
                + " | Size: " + size
                + " | Crust: " + crust
                + " | Toppings: " + toppings
                + String.format(" | $%.2f", price());
    }
}
