package dev.tiagosilva.business;

import dev.tiagosilva.dto.AddressRequestDTO;
import dev.tiagosilva.dto.AddressResponseDTO;
import dev.tiagosilva.model.AddressModel;

import java.util.ArrayList;
import java.util.List;

public class AddressBusinessHandler {
    public AddressModel address;

    public boolean create(AddressRequestDTO addressDto) {
        try {
            address = new AddressModel();
            address.setComp(addressDto.getComp());
            address.setStreet(addressDto.getStreet());
            address.setNumber(addressDto.getNumber());
            address.setNeighborhood(addressDto.getNeighborhood());
            address.setId_user(addressDto.getId_user());
            address.save();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return true;
    }

    public List<AddressResponseDTO> get() {
        List<AddressResponseDTO> addressesResponseDTO = new java.util.ArrayList<>();

        try {
            address = new AddressModel();
            List<AddressModel> results = address.list();
            if (!results.isEmpty()) {
                results.forEach(result -> {
                    AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
                    address.setComp(result.getComp());
                    address.setStreet(result.getStreet());
                    address.setNumber(result.getNumber());
                    address.setNeighborhood(result.getNeighborhood());
                    address.setId_user(result.getId_user());
                    address.setId(result.getId());
                    address.setCreatedAt(result.getCreatedAt());
                    addressesResponseDTO.add(addressResponseDTO);
                });
            }else {
                throw new RuntimeException("Produtos não encontrados");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return addressesResponseDTO;
    }

    public List<AddressResponseDTO> getByUser(Long id) {
        List<AddressResponseDTO> addressesDTO = new ArrayList<>();

        try {
            address = new AddressModel();
            List<AddressModel> results = address.list("id_user", id.toString(), false);
            results.forEach(result -> {
                if (result != null) {
                    AddressResponseDTO addressDto = new AddressResponseDTO();
                    addressDto.setComp(result.getComp());
                    addressDto.setStreet(result.getStreet());
                    addressDto.setNumber(result.getNumber());
                    addressDto.setNeighborhood(result.getNeighborhood());
                    addressDto.setId_user(result.getId_user());
                    addressDto.setId(result.getId());
                    addressesDTO.add(addressDto);
                }else {
                    throw new RuntimeException("Produto não encontrado");
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return addressesDTO;
    }

    public AddressResponseDTO get(Long id) {
        AddressResponseDTO addressseDTO = new AddressResponseDTO();

        try {
            address = new AddressModel();
            AddressModel result = address.find(id);
            if (result != null) {
                addressseDTO.setComp(result.getComp());
                addressseDTO.setStreet(result.getStreet());
                addressseDTO.setNumber(result.getNumber());
                addressseDTO.setNeighborhood(result.getNeighborhood());
                addressseDTO.setId_user(result.getId_user());
                addressseDTO.setId(result.getId());
            }else {
                throw new RuntimeException("Produto não encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return addressseDTO;
    }

    public boolean update(Long id, AddressRequestDTO addressDto) {
        try {
            address = new AddressModel();
            AddressModel result = address.find(id);
            if (result == null) {
                return false;
            }else {
                address.setComp(addressDto.getComp());
                address.setStreet(result.getStreet());
                address.setNumber(result.getNumber());
                address.setNeighborhood(result.getNeighborhood());
                address.setId_user(result.getId_user());
                address.setId(result.getId());
                address.setCreatedAt(result.getCreatedAt());
                address.update(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return true;
    }

    public boolean delete(Long id) {
        try {
            address = new AddressModel();
            address.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return true;
    }
}
