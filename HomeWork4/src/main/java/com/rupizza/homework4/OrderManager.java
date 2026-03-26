package com.rupizza.homework4;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class OrderManager {
    private ArrayList<Order> orders;
    private int nextOrderNumber;

    /**
     * Constructor for OrderManager, initializes the order list and order number counter.
     */
    public OrderManager() {
        orders = new ArrayList<>();
        nextOrderNumber = 1;
    }

    /**
     * Creates a new Order with a unique order number without adding it to the list.
     * @return a new Order object
     */
    public Order createOrder() {
        return new Order(nextOrderNumber++);
    }

    /**
     * Places an order by adding it to the list of orders.
     * @param order the Order to place
     */
    public void placeOrder(Order order) {
        orders.add(order);
    }

    /**
     * Cancels an order by removing it from the list of orders.
     * @param order the Order to cancel
     */
    public void cancelOrder(Order order) {
        orders.remove(order);
    }

    /**
     * Returns the list of all placed orders.
     * @return ArrayList of Order
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Exports all placed orders to a text file.
     * @param filename the name of the file to write to
     */
    public void exportOrders(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Order order : orders) {
                writer.write("Order #" + order.getNumber() + "\n");
                for (Pizza pizza : order.getPizzas()) {
                    writer.write("  " + pizza.toString() + "\n");
                }
                writer.write(String.format("  Order Total: $%.2f%n", order.getTotal()));
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

