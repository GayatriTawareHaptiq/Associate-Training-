public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int categoryId;

    public Product(int id, String name, double price, int stock, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public int getCategoryId() { return categoryId; }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Price: $%.2f, Stock: %d, Category ID: %d", id, name, price, stock, categoryId);
    }
}