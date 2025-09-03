package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentModel extends Model<PaymentModel> {
    private String name;
    private String number;
    private String secret;
    private int month;
    private int year;
    private Long id_user;

    public PaymentModel() {
        super("payments.csv");
    }
}
