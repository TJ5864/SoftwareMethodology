package com.example.myapplication;

/** Holds the display data for one pizza row in the RecyclerView menu. */
public class PizzaOption {
    private final String name;
    private final String toppings;
    private final int imageResId;
    private final String style;
    private final String pizzaType;

    /** @param name       display name e.g. "NY Style Deluxe"
     *  @param toppings   topping list shown in the row
     *  @param imageResId drawable resource for the pizza photo
     *  @param style      "NY" or "Chicago"
     *  @param pizzaType  "Deluxe", "BBQChicken", "Meatzza", or "BuildYourOwn" */
    public PizzaOption(String name, String toppings, int imageResId, String style, String pizzaType) {
        this.name = name;
        this.toppings = toppings;
        this.imageResId = imageResId;
        this.style = style;
        this.pizzaType = pizzaType;
    }

    public String getName()      { return name; }
    public String getToppings()  { return toppings; }
    public int getImageResId()   { return imageResId; }
    public String getStyle()     { return style; }
    public String getPizzaType() { return pizzaType; }
}
