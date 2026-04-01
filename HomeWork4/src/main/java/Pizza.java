package main.java;

import java.util.ArrayList;

public abstract class Pizza {
    private ArrayList<Topping> toppings; //Topping is a enum class
    private Crust crust; //Crust is a enum class
    private Size size; //Size is a enum class
    /** Abstract Price method, works with the price of the pizza*/
    public abstract double price(); //polymorphism
/** Constructor for Pizza
 * creates an array list of toppings */
    public Pizza(){
        toppings = new ArrayList<>();
    }
/** Getter Method, this is used to get the list of toppings for the pizza
 * @return the list of toppings  */
    public ArrayList<Topping> getToppings(){
        return toppings;

    }
/** Getter method for crust,
 * @return the curretn crust  */
    public Crust getCrust(){
        return crust;
    }
    /** Sets the value of crust
     * @param crust the type of crust we want for the pizza
     * */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**  Getter method for Size
     * @return size of pizza small, medium or large */
    public Size getSize(){
        return size;
        }

    /** Set the size of the pizza
     * @param  size the size of the pizza being ordered*/
    public void setSize(Size size){
        this.size = size;
    }

}