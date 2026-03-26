package com.rupizza.homework4;
import com.rupizza.homework4.Size;

public class BBQChicken extends Pizza{
    @Override
    public double price(){
        if(getSize() == Size.SMALL) return 16.99;
        if(getSize() == Size.MEDIUM)return 18.99;
        return 20.99;

    }
}
