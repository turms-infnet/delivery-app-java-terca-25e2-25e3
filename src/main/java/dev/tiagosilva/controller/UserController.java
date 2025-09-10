package dev.tiagosilva.controller;

import dev.tiagosilva.dto.UserResponseDTO;

import java.util.List;

public class UserController extends Controller<UserResponseDTO>{
    @Override
    public List<UserResponseDTO> get() {
        return List.of();
    }

    @Override
    public UserResponseDTO get(Long id) {
        return null;
    }

    @Override
    public UserResponseDTO create(UserResponseDTO obj) {
        return null;
    }

    @Override
    public UserResponseDTO update(Long id, UserResponseDTO obj) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
