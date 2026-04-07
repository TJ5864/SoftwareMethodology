package main.java;

/** Represents a Build Your Own pizza where the customer selects up to 5 toppings. */
public class BuildYourOwn extends Pizza{
    /** Returns the price of the Build Your Own pizza based on size and number of toppings.
     * Base price plus $1.69 per topping.
     * @return price as a double */
    @Override
    public double price(){
        double base;
        if(getSize() == Size.SMALL) base = 10.99;
        else if(getSize() == Size.MEDIUM) base = 12.99;
        else  base = 14.99;
        return base + (getToppings().size() * 1.69);
    }

    /** Adds a topping to the pizza, up to a maximum of 5 toppings.
     * @param topping the topping to add
     * @return true if added, false if the 5-topping limit has been reached */
    @Override
    public boolean addTopping(Topping topping){
        if(getToppings().size()>= 5) return false;
        getToppings().add(topping);
        return true;
    }
}
