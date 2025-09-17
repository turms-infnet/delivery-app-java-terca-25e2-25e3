package dev.tiagosilva.business;

import dev.tiagosilva.dto.ProductRequestDTO;
import dev.tiagosilva.dto.ProductResponseDTO;
import dev.tiagosilva.dto.UserResponseDTO;
import dev.tiagosilva.model.ProductModel;
import dev.tiagosilva.model.UserModel;

import java.util.List;

public class ProductBusinessHandler {
    public ProductModel product;

    public boolean create(ProductRequestDTO productDto) {
        try {
            product = new ProductModel();
            ProductModel result = product.find("name", productDto.getName());
            if (result != null) {
                return false;
            }else {
                product.setName(productDto.getName());
                product.setPrice(productDto.getPrice());
                product.save();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return true;
    }

    public List<ProductResponseDTO> get() {
        List<ProductResponseDTO> productsResponseDTO = new java.util.ArrayList<>();

        try {
            product = new ProductModel();
            List<ProductModel> results = product.list();
            if (!results.isEmpty()) {
                results.forEach(product -> {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO();
                    productResponseDTO.setId(product.getId());
                    productResponseDTO.setName(product.getName());
                    productResponseDTO.setPrice(product.getPrice());
                    productsResponseDTO.add(productResponseDTO);
                });
            }else {
                throw new RuntimeException("Produtos não encontrados");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return productsResponseDTO;
    }

    public ProductResponseDTO get(Long id) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        try {
            product = new ProductModel();
            ProductModel result = product.find(id);
            if (result != null) {
                productResponseDTO.setId(result.getId());
                productResponseDTO.setName(result.getName());
                productResponseDTO.setPrice(result.getPrice());
            }else {
                throw new RuntimeException("Produto não encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return productResponseDTO;
    }
}