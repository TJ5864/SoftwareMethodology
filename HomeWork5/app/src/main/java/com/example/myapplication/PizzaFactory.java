package com.example.myapplication;

/** Interface defining the Abstract Factory for creating pizzas.
 *  Implementations create pizzas with the appropriate crust and toppings for their style. */
public interface PizzaFactory {
    /** Creates a Deluxe pizza with the appropriate crust and toppings.
     * @return a Deluxe Pizza */
    Pizza createDeluxe();

    /** Creates a Meatzza pizza with the appropriate crust and toppings.
     * @return a Meatzza Pizza */
    Pizza createMeatzza();

    /** Creates a BBQ Chicken pizza with the appropriate crust and toppings.
     * @return a BBQ Chicken Pizza */
    Pizza createBBQChicken();

    /** Creates a Build Your Own pizza with the appropriate crust and no toppings.
     * @return a BuildYourOwn Pizza */
    Pizza createBuildYourOwn();
}
