package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserModel extends Model<UserModel> {
    private String username;
    private String password;
    private int role;
    private AddressModel address;
    private PaymentModel payment;

    public UserModel() {
        super("users.csv", List.of("address", "payment"));
    }
}
