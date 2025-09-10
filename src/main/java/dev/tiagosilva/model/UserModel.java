package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Getter
@Setter
public class UserModel extends Model {
    private String username;
    private String password;
    private int role;
    private AddressModel address;
    private PaymentModel payment;

    public UserModel() {
        super("users.csv");
    }

    public UserModel(String username, String password, int role) {
        super("users.csv", List.of("address", "payment"));
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
