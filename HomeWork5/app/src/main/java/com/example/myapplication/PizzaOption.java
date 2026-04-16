package com.example.myapplication;

/** Holds the display data for one pizza row in the RecyclerView menu. */
public class PizzaOption {
    private final String name;
    private final String toppings;
    private final int imageResId;

    /** @param name       display name e.g. "NY Style Deluxe"
     *  @param toppings   topping list shown in the row
     *  @param imageResId drawable resource for the pizza photo */
    public PizzaOption(String name, String toppings, int imageResId) {
        this.name = name;
        this.toppings = toppings;
        this.imageResId = imageResId;
    }

    public String getName()     { return name; }
    public String getToppings() { return toppings; }
    public int getImageResId()  { return imageResId; }
}
