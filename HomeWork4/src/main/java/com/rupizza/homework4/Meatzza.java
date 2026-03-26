package com.rupizza.homework4;

public class Meatzza extends Pizza{
    @Override
    public double price(){
        if(getSize() == Size.SMALL) return 19.99;
        if(getSize() == Size.MEDIUM) return 21.99;
        return 23.99;
    }
}
