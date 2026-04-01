package main.java;

public interface PizzaFactory {
    Pizza createDeluxe(); //create a Deluxe object and set the toppings/crust
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}
