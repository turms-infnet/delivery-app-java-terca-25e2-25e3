package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressModel extends Model<AddressModel> {
    private String street;
    private int number;
    private String comp;
    private String neighborhood;
    private Long id_user;

    public AddressModel() {
        super("addresses.csv");
    }
}
