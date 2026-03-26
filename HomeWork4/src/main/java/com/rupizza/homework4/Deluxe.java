package com.rupizza.homework4;
import com.rupizza.homework4.Size;

public class Deluxe  extends  Pizza{
    @Override
    public double price() {
        if (getSize() == Size.SMALL) return 18.99;
        if (getSize() == Size.MEDIUM) return 20.99;
        return 22.99;

    }
}

