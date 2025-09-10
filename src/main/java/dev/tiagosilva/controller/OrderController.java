package dev.tiagosilva.controller;

import dev.tiagosilva.dto.OrderRequestDTO;
import dev.tiagosilva.dto.OrderResponseDTO;

import java.util.List;

public class OrderController extends Controller<OrderResponseDTO>{
    @Override
    public List<OrderResponseDTO> get() {
        return List.of();
    }

    @Override
    public OrderResponseDTO get(Long id) {
        return null;
    }

    @Override
    public OrderResponseDTO create(OrderResponseDTO obj) {
        return null;
    }

    @Override
    public OrderResponseDTO update(Long id, OrderResponseDTO obj) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
