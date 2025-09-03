package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductModel extends Model<ProductModel> {
    private String name;
    private Float price;

    public ProductModel() {
        super("products.csv");
    }
}
