package dev.tiagosilva.model;


public class ProductModel extends Model<ProductModel> {
    private String name;
    private Float price;

    public ProductModel() {
        super("products.csv");
    }

    public ProductModel(String name, Float price) {
        super("products.csv");
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
