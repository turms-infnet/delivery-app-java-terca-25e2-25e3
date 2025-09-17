package dev.tiagosilva.controller;

import dev.tiagosilva.dto.OrderRequestDTO;
import dev.tiagosilva.dto.OrderResponseDTO;

import java.util.List;

public class OrderController{
    public List<OrderResponseDTO> get() {
        return List.of();
    }

    public OrderResponseDTO get(Long id) {
        return null;
    }

    public OrderResponseDTO create(OrderResponseDTO obj) {
        return null;
    }

    public OrderResponseDTO update(Long id, OrderResponseDTO obj) {
        return null;
    }

    public boolean delete(Long id) {
        return false;
    }
}
