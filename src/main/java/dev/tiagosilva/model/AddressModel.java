package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AddressModel extends Model<AddressModel> {
    private String street;
    private int number;
    private String comp;
    private String neighborhood;
    private Long id_user;

    public AddressModel() {
        super("addresses.csv");
    }

    public AddressModel(String street, int number, String comp, String neighborhood, Long id_user) {
        super("addresses.csv");
        this.street = street;
        this.number = number;
        this.comp = comp;
        this.neighborhood = neighborhood;
        this.id_user = id_user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
}
