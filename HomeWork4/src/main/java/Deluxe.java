package main.java;

/** Represents a Deluxe specialty pizza. Crust and toppings are set by the factory. */
public class Deluxe  extends  Pizza{
    /** Returns the price of the Deluxe pizza based on size.
     * @return price as a double */
    @Override
    public double price() {
        if (getSize() == Size.SMALL) return 18.99;
        if (getSize() == Size.MEDIUM) return 20.99;
        return 22.99;

    }
}

