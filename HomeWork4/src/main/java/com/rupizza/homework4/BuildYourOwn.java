package com.rupizza.homework4;

public class BuildYourOwn extends Pizza{
    @Override
    public double price(){
        double base;
        if(getSize() == Size.SMALL) base = 10.99;
        else if(getSize() == Size.MEDIUM) base = 12.99;
        else  base = 14.99;
        return base + (getToppings().size() * 1.69);
    }

    public boolean addTopping(Topping topping){
        if(getToppings().size()>= 5) return false;
        getToppings().add(topping);
        return true;

    }

    public boolean removeTopping(Topping topping){
        return getToppings().remove(topping);
    }
}
