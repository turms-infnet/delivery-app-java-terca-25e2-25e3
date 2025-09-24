package dev.tiagosilva.dto;

import dev.tiagosilva.model.AddressModel;
import dev.tiagosilva.model.PaymentModel;
import dev.tiagosilva.controller.Controller;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

public class UserResponseDTO {
    private Long id;
    private String username;
    private int role;
    private List<AddressResponseDTO> addresses;

    public UserResponseDTO() {}

    public UserResponseDTO(Long id, String username, String password, int role) throws IOException {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public List<AddressResponseDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressResponseDTO> addresses) {
        this.addresses = addresses;
    }
}
