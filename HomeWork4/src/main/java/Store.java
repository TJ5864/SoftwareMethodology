package main.java;

/** Holds the global application state: the shared OrderManager and the current active Order. */
public class Store {
    public static final OrderManager orderManager = new OrderManager();
    public static Order currentOrder = orderManager.createOrder();
}
