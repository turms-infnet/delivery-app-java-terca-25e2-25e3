package dev.tiagosilva.controller;

import dev.tiagosilva.business.AddressBusinessHandler;
import dev.tiagosilva.dto.AddressRequestDTO;
import dev.tiagosilva.dto.AddressResponseDTO;

import java.util.List;

public class AddressController{
    public AddressBusinessHandler addressBusinessHandler = new AddressBusinessHandler();

    public boolean create(AddressRequestDTO addressDto) {
        return addressBusinessHandler.create(addressDto);
    }

    public List<AddressResponseDTO> get() {
        return addressBusinessHandler.get();
    }

    public AddressResponseDTO get(Long id) {
        return addressBusinessHandler.get(id);
    }

    public boolean update(Long id, AddressRequestDTO addressDto) {
        return addressBusinessHandler.update(id, addressDto);
    }

    public boolean delete(Long id) {
        return addressBusinessHandler.delete(id);
    }
}
