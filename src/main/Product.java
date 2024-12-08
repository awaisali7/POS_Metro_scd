package main;

// Helper class to represent a Product
public class Product {
    private final String name;
    private final double price;
    private int stock; // For inventory products
    private int quantity; // For cart items

    // Constructor for inventory products
    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.quantity = 0; // Default value for cart quantity
    }

    // Constructor for cart items
    public Product(String name, double price, int quantity, boolean isForCart) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.stock = 0; // Default value for inventory stock
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    public void reduceStock(int amount) {
        this.stock -= amount;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Price: %.2f, Stock: %d", name, price, stock);
    }
}
