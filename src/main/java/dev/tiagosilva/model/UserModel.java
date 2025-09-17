package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

public class UserModel extends Model<UserModel> {
    private String username;
    private String password;
    private int role;
    private AddressModel address;
    private PaymentModel payment;

    public UserModel() {
        super("users.csv", List.of("address", "payment"));
    }

    public UserModel(String username, String password, int role) {
        super("users.csv", List.of("address", "payment"));
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public PaymentModel getPayment() {
        return payment;
    }

    public void setPayment(PaymentModel payment) {
        this.payment = payment;
    }
}
