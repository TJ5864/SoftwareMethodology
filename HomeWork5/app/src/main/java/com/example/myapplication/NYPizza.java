package com.example.myapplication;

/** Concrete factory that creates New York-style pizzas with their designated crusts and toppings. */
public class NYPizza implements PizzaFactory {

    /** Creates a New York-style Deluxe pizza with Brooklyn crust.
     * @return a Deluxe Pizza */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust(Crust.BROOKLYN);
        pizza.getToppings().add(Topping.SAUSAGE);
        pizza.getToppings().add(Topping.PEPPERONI);
        pizza.getToppings().add(Topping.GREEN_PEPPER);
        pizza.getToppings().add(Topping.ONION);
        pizza.getToppings().add(Topping.MUSHROOM);
        return pizza;
    }

    /** Creates a New York-style BBQ Chicken pizza with Thin crust.
     * @return a BBQChicken Pizza */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust(Crust.THIN);
        pizza.getToppings().add(Topping.BBQ_CHICKEN);
        pizza.getToppings().add(Topping.GREEN_PEPPER);
        pizza.getToppings().add(Topping.PROVOLONE);
        pizza.getToppings().add(Topping.CHEDDAR);
        return pizza;
    }

    /** Creates a New York-style Meatzza pizza with Hand-Tossed crust.
     * @return a Meatzza Pizza */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.HAND_TOSSED);
        pizza.getToppings().add(Topping.SAUSAGE);
        pizza.getToppings().add(Topping.HAM);
        pizza.getToppings().add(Topping.PEPPERONI);
        pizza.getToppings().add(Topping.BEEF);
        return pizza;
    }

    /** Creates a New York-style Build Your Own pizza with Hand-Tossed crust and no toppings.
     * @return a BuildYourOwn Pizza */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.HAND_TOSSED);
        return pizza;
    }
}
