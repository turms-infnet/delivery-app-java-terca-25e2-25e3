package dev.tiagosilva.controller;

import dev.tiagosilva.business.ProductBusinessHandler;
import dev.tiagosilva.dto.ProductRequestDTO;
import dev.tiagosilva.dto.ProductResponseDTO;

import java.util.List;

public class ProductController{
    public ProductBusinessHandler productBusinessHandler = new ProductBusinessHandler();


    public List<ProductResponseDTO> get() {
        return productBusinessHandler.get();
    }

    public ProductResponseDTO get(Long id) {
        return productBusinessHandler.get(id);
    }

    public boolean create(ProductRequestDTO obj) {
        return productBusinessHandler.create(obj);
    }

    public boolean update(Long id, ProductRequestDTO obj) {
        return productBusinessHandler.update(id, obj);
    }

    public boolean delete(Long id) {
        return productBusinessHandler.delete(id);
    }

    public List<ProductResponseDTO> addToCard(List<ProductRequestDTO> objs) {
        return null;
    }
}
