package main.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for the BuildYourOwn pizza price() method.
 * Uses Black-Box testing: tests are based on the specification
 * (base price by size + $1.69 per topping, up to 5 toppings).
 */
public class BuildYourOwnTest {

    private static final double DELTA = 0.001;

    /** Small, 0 toppings → $10.99 */
    @Test
    public void testSmallNoToppings() {
        Pizza pizza = new BuildYourOwn();
        pizza.setSize(Size.SMALL);
        assertEquals(10.99, pizza.price(), DELTA);
    }

    /** Small, 1 topping → $10.99 + $1.69 = $12.68 */
    @Test
    public void testSmallOneTopping() {
        Pizza pizza = new BuildYourOwn();
        pizza.setSize(Size.SMALL);
        pizza.addTopping(Topping.PEPPERONI);
        assertEquals(12.68, pizza.price(), DELTA);
    }

    /** Small, 5 toppings (max) → $10.99 + 5*$1.69 = $19.44 */
    @Test
    public void testSmallMaxToppings() {
        Pizza pizza = new BuildYourOwn();
        pizza.setSize(Size.SMALL);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.GREEN_PEPPER);
        assertEquals(19.44, pizza.price(), DELTA);
    }

    /** Medium, 0 toppings → $12.99 */
    @Test
    public void testMediumNoToppings() {
        Pizza pizza = new BuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        assertEquals(12.99, pizza.price(), DELTA);
    }

    /** Medium, 3 toppings → $12.99 + 3*$1.69 = $18.06 */
    @Test
    public void testMediumThreeToppings() {
        Pizza pizza = new BuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.CHEDDAR);
        assertEquals(18.06, pizza.price(), DELTA);
    }

    /** Large, 0 toppings → $14.99 */
    @Test
    public void testLargeNoToppings() {
        Pizza pizza = new BuildYourOwn();
        pizza.setSize(Size.LARGE);
        assertEquals(14.99, pizza.price(), DELTA);
    }

    /** Large, 5 toppings (max) → $14.99 + 5*$1.69 = $23.44 */
    @Test
    public void testLargeMaxToppings() {
        Pizza pizza = new BuildYourOwn();
        pizza.setSize(Size.LARGE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.PROVOLONE);
        assertEquals(23.44, pizza.price(), DELTA);
    }
}
