package dev.tiagosilva.business;

import dev.tiagosilva.dto.AddressResponseDTO;
import dev.tiagosilva.dto.UserRequestDTO;
import dev.tiagosilva.dto.UserResponseDTO;
import dev.tiagosilva.model.UserModel;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AuthBusinessHandler {
    public UserModel user;
    public AddressBusinessHandler addressBusinessHandler = new AddressBusinessHandler();

    public boolean register(UserRequestDTO userDTO) {
        try {
            user = new UserModel();
            UserModel result = user.find("username", userDTO.getUsername());
            if (result != null) {
                return false;
            }else {
                user.setUsername(userDTO.getUsername());
                user.setPassword(gerarHash(userDTO.getPassword()));
                user.setRole(userDTO.getRole());
                user.save();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return true;
    }

    public UserResponseDTO login(UserRequestDTO userDTO) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        try {
            user = new UserModel();
            UserModel result = user.find("username", userDTO.getUsername());
            if (result != null && validarHash(userDTO.getPassword(), result.getPassword())) {
                List<AddressResponseDTO> addresses = addressBusinessHandler.getByUser(result.getId());

                userResponseDTO.setId(result.getId());
                userResponseDTO.setUsername(result.getUsername());
                userResponseDTO.setRole(result.getRole());
                userResponseDTO.setAddresses(addresses);
            }else {
                throw new RuntimeException("Usuário ou senha inválidos");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return userResponseDTO;
    }

    private String gerarHash(String texto) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();

        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

    public boolean validarHash(String texto, String hashEsperado) throws NoSuchAlgorithmException {
        String hashGerado = this.gerarHash(texto);
        return hashGerado.equalsIgnoreCase(hashEsperado);
    }
}
