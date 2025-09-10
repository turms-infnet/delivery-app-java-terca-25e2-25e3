package dev.tiagosilva.controller;

import dev.tiagosilva.dto.PaymentResponseDTO;

import java.util.List;

public class PaymentController extends Controller<PaymentResponseDTO>{
    @Override
    public List<PaymentResponseDTO> get() {
        return List.of();
    }

    @Override
    public PaymentResponseDTO get(Long id) {
        return null;
    }

    @Override
    public PaymentResponseDTO create(PaymentResponseDTO obj) {
        return null;
    }

    @Override
    public PaymentResponseDTO update(Long id, PaymentResponseDTO obj) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
