package dev.tiagosilva.controller;

import dev.tiagosilva.dto.ProductResponseDTO;

import java.util.List;

public class ProductController extends Controller<ProductResponseDTO>{
    @Override
    public List<ProductResponseDTO> get() {
        return List.of();
    }

    @Override
    public ProductResponseDTO get(Long id) {
        return null;
    }

    @Override
    public ProductResponseDTO create(ProductResponseDTO obj) {
        return null;
    }

    @Override
    public ProductResponseDTO update(Long id, ProductResponseDTO obj) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
