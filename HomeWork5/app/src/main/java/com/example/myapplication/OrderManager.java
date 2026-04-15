package com.example.myapplication;

import java.util.ArrayList;

/** Manages all placed orders and provides order lifecycle operations (create, place, cancel). */
public class OrderManager {
    private ArrayList<Order> orders;
    private int nextOrderNumber;
    private static final double NJ_TAX_RATE = 0.06625;

    /** Constructor for OrderManager, initializes the order list and order number counter. */
    public OrderManager() {
        orders = new ArrayList<>();
        nextOrderNumber = 1;
    }

    /** Creates a new Order with a unique order number without adding it to the list.
     * @return a new Order object */
    public Order createOrder() {
        return new Order(nextOrderNumber++);
    }

    /** Places an order by adding it to the list of orders.
     * @param order the Order to place */
    public void placeOrder(Order order) {
        orders.add(order);
    }

    /** Cancels an order by removing it from the list of orders.
     * @param order the Order to cancel */
    public void cancelOrder(Order order) {
        orders.remove(order);
    }

    /** Returns the list of all placed orders.
     * @return ArrayList of Order */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /** Returns the NJ tax rate.
     * @return tax rate as a double */
    public double getTaxRate() {
        return NJ_TAX_RATE;
    }
}
