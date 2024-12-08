=package main;

public class Product {
    private int id;
    private String name;
    private String category;
    private double originalPrice;
    private double salePrice;
    private int stock;

    public Product(int id, String name, String category, double originalPrice, double salePrice, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Category: %s, Price: %.2f, Stock: %d",
                id, name, category, salePrice, stock);
    }
}
