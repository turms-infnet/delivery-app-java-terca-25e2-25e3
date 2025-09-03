package dev.tiagosilva;

import dev.tiagosilva.model.ProductModel;
import dev.tiagosilva.model.UserModel;

public class Main {
    public static void main(String[] args) {
        ProductModel productModel = new ProductModel();
        productModel.setName("Produto teste");
        productModel.setPrice(19.99F);
        productModel.save();

        UserModel userModel = new UserModel();
        userModel.setUsername("Usuário teste");
        userModel.setPassword("123");
        userModel.setRole(1);
        userModel.save();
    }
}