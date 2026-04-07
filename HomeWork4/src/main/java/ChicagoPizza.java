package main.java;


/** Concrete factory that creates Chicago-style pizzas with their designated crusts and toppings. */
public class ChicagoPizza implements PizzaFactory {
    /** Creates a Chicago-style Deluxe pizza with Deep Dish crust.
     * @return a Deluxe Pizza */
    @Override
    public Pizza createDeluxe(){
        Pizza pizza  = new Deluxe();
        pizza.setCrust(Crust.DEEP_DISH);
        pizza.getToppings().add(Topping.SAUSAGE);
        pizza.getToppings().add(Topping.PEPPERONI);
        pizza.getToppings().add(Topping.GREEN_PEPPER);
        pizza.getToppings().add(Topping.ONION);
        pizza.getToppings().add(Topping.MUSHROOM);
        return pizza;
    }
    /** Creates a Chicago-style BBQ Chicken pizza with Pan crust.
     * @return a BBQChicken Pizza */
    @Override
    public Pizza createBBQChicken(){
        Pizza pizza  = new BBQChicken();
        pizza.setCrust(Crust.PAN);
        pizza.getToppings().add(Topping.BBQ_CHICKEN);
        pizza.getToppings().add(Topping.GREEN_PEPPER);
        pizza.getToppings().add(Topping.PROVOLONE);
        pizza.getToppings().add(Topping.CHEDDAR);
        return pizza;

    }
    /** Creates a Chicago-style Meatzza pizza with Stuffed crust.
     * @return a Meatzza Pizza */
    @Override
    public Pizza createMeatzza(){
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.STUFFED);
        pizza.getToppings().add(Topping.SAUSAGE);
        pizza.getToppings().add(Topping.HAM);
        pizza.getToppings().add(Topping.PEPPERONI);
        pizza.getToppings().add(Topping.BEEF);
        return pizza;
    }
    /** Creates a Chicago-style Build Your Own pizza with Pan crust and no toppings.
     * @return a BuildYourOwn Pizza */
    @Override
    public Pizza createBuildYourOwn(){
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.PAN);
        return pizza;
    }



}
