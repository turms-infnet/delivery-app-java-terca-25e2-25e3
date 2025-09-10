package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel extends Model {
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
}
