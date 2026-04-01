package main.java;

public class NYPizza implements PizzaFactory{

    @Override
    public Pizza createDeluxe(){
        Pizza pizza  = new Deluxe();
        pizza.setCrust(Crust.BROOKLYN);
        pizza.getToppings().add(Topping.SAUSAGE);
        pizza.getToppings().add(Topping.PEPPERONI);
        pizza.getToppings().add(Topping.GREEN_PEPPER);
        pizza.getToppings().add(Topping.ONION);
        pizza.getToppings().add(Topping.MUSHROOM);
        return pizza;
    }
    @Override
    public Pizza createBBQChicken(){
        Pizza pizza  = new BBQChicken();
        pizza.setCrust(Crust.THIN);
        pizza.getToppings().add(Topping.BBQ_CHICKEN);
        pizza.getToppings().add(Topping.GREEN_PEPPER);
        pizza.getToppings().add(Topping.PROVOLONE);
        pizza.getToppings().add(Topping.CHEDDAR);
        return pizza;

    }
    @Override
    public Pizza createMeatzza(){
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.HAND_TOSSED);
        pizza.getToppings().add(Topping.SAUSAGE);
        pizza.getToppings().add(Topping.HAM);
        pizza.getToppings().add(Topping.PEPPERONI);
        pizza.getToppings().add(Topping.BEEF);
        return pizza;
    }
    @Override
    public Pizza createBuildYourOwn(){
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.HAND_TOSSED);
        return pizza;
    }
}
