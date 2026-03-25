package com.rupizza.homework4;

public abstract class Pizza {
    private ArrayList<Topping> toppings; //Topping is a enum class
    private Crust crust; //Crust is a enum class
    private Size size; //Size is a enum class
    public abstract double price(); //polymorphism
}