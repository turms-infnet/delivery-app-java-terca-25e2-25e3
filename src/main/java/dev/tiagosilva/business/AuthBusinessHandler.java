package dev.tiagosilva.business;

import dev.tiagosilva.dto.UserRequestDTO;
import dev.tiagosilva.dto.UserResponseDTO;
import dev.tiagosilva.model.UserModel;

public class AuthBusinessHandler {
    public UserModel user;

    public boolean register(UserRequestDTO userDTO) {
        try {
            user = new UserModel();
            UserModel result = user.find("username", userDTO.getUsername());
            if (result != null) {
                return false;
            }else {
                user.setUsername(userDTO.getUsername());
                user.setPassword(userDTO.getPassword());
                user.setRole(userDTO.getRole());
                user.save();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return true;
    }

    public UserResponseDTO login(UserRequestDTO userDTO) {
        UserResponseDTO userRsponseDTO = new UserResponseDTO();

        try {
            user = new UserModel();
            UserModel result = user.find("username", userDTO.getUsername());
            if (result != null && result.getPassword().equals(userDTO.getPassword())) {
                userRsponseDTO.setId(result.getId());
                userRsponseDTO.setUsername(result.getUsername());
                userRsponseDTO.setRole(result.getRole());
            }else {
                throw new RuntimeException("Usuário ou senha inválidos");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return userRsponseDTO;
    }
}
