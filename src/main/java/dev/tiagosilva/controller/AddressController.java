package dev.tiagosilva.controller;

import dev.tiagosilva.dto.AddressResponseDTO;

import java.util.List;

public class AddressController extends Controller<AddressResponseDTO>{
    @Override
    public List<AddressResponseDTO> get() {
        return List.of();
    }

    @Override
    public AddressResponseDTO get(Long id) {
        return null;
    }

    @Override
    public AddressResponseDTO create(AddressResponseDTO obj) {
        return null;
    }

    @Override
    public AddressResponseDTO update(Long id, AddressResponseDTO obj) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
