package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentModel extends Model {
    private String name;
    private String number;
    private int secret;
    private int month;
    private int year;
    private Long id_user;

    public PaymentModel() {
        super("payments.csv");
    }

    public PaymentModel(String name, String number, int secret, int month, int year, Long id_user) {
        super("payments.csv");
        this.name = name;
        this.number = number;
        this.secret = secret;
        this.month = month;
        this.year = year;
        this.id_user = id_user;
    }
}
