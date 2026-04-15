package com.example.myapplication;

/** Singleton class that holds the global application state: the shared OrderManager and
 *  the current active Order. Required for sharing data across Android Activities. */
public class StoreManager {
    private static StoreManager instance;
    private OrderManager orderManager;
    private Order currentOrder;

    /** Private constructor — use getInstance() instead. */
    private StoreManager() {
        orderManager = new OrderManager();
        currentOrder = orderManager.createOrder();
    }

    /** Returns the single instance of StoreManager, creating it if necessary.
     * @return the StoreManager singleton */
    public static StoreManager getInstance() {
        if (instance == null) {
            instance = new StoreManager();
        }
        return instance;
    }

    /** Returns the shared OrderManager.
     * @return the OrderManager */
    public OrderManager getOrderManager() {
        return orderManager;
    }

    /** Returns the current active Order being built.
     * @return the current Order */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /** Replaces the current order with a fresh one after placing an order. */
    public void startNewOrder() {
        currentOrder = orderManager.createOrder();
    }
}
