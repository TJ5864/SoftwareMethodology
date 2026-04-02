package main.java;

public class Store {
    public static final OrderManager orderManager = new OrderManager();
    public static Order currentOrder = orderManager.createOrder();
}
