package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class OrderModel extends Model<OrderModel> {
    private LocalDateTime date;
    private Float total;
    private String products;
    private int status;
    private String comment;
    private int rating;
    private long id_user;

    public OrderModel() {
        super("orders.csv");
    }
}
