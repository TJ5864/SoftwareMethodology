package main.java;

/** Represents a Meatzza specialty pizza. Crust and toppings are set by the factory. */
public class Meatzza extends Pizza{
    /** Returns the price of the Meatzza pizza based on size.
     * @return price as a double */
    @Override
    public double price(){
        if(getSize() == Size.SMALL) return 19.99;
        if(getSize() == Size.MEDIUM) return 21.99;
        return 23.99;
    }
}
