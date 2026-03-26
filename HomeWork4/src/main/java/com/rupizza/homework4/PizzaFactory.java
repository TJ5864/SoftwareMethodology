package com.rupizza.homework4;

public interface PizzaFactory {
    Pizza createDeluxe(); //create a Deluxe object and set the toppings/crust
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}
