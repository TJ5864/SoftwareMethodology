package main.java;

/** Represents a BBQ Chicken specialty pizza. Crust and toppings are set by the factory. */
public class BBQChicken extends Pizza{
    /** Returns the price of the BBQ Chicken pizza based on size.
     * @return price as a double */
    @Override
    public double price(){
        if(getSize() == Size.SMALL) return 16.99;
        if(getSize() == Size.MEDIUM)return 18.99;
        return 20.99;

    }
}
