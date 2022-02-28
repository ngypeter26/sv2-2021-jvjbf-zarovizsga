package webshop;

public class Product {
    private String productName;
    private int price;
    private int stock;
    private long id;

    public Product(String productName, int price, int stock) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }
    public Product(long id,String productName, int price, int stock) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public long getId() {
        return id;
    }


}
