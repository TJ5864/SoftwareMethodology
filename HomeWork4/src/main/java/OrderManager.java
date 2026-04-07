package main.java;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OrderManager {
    private ArrayList<Order> orders;
    private int nextOrderNumber;
    private static final double NJ_TAX_RATE = 0.06625;

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
        FileWriter writer = null;

        try {
            File file = new File(filename);
            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            writer = new FileWriter(file);

            for (Order order : orders) {
                double subtotal = order.getTotal();
                double salesTax = subtotal * NJ_TAX_RATE;
                double total = subtotal + salesTax;

                writer.write("Order #" + order.getNumber() + "\n");
                for (Pizza pizza : order.getPizzas()) {
                    writer.write("  " + pizza.toString() + "\n");
                }
                writer.write(String.format("  Order Total: $%.2f%n", total));
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not export orders to file: " + filename, e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("Could not close file: " + filename, e);
                }
            }
        }
    }
}