package dev.tiagosilva.controller;

import dev.tiagosilva.business.AuthBusinessHandler;
import dev.tiagosilva.dto.UserRequestDTO;
import dev.tiagosilva.dto.UserResponseDTO;

import java.util.List;

public class UserController{
    public AuthBusinessHandler authBusinessHandler = new AuthBusinessHandler();

    public boolean register(UserRequestDTO userRequestDTO) {
        return authBusinessHandler.register(userRequestDTO);
    }

    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        return authBusinessHandler.login(userRequestDTO);
    }
}
