package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderModel extends Model {
    private LocalDateTime date;
    private Float total;
    private int status;
    private String comments;
    private int rating;
    private String products;
    private Long id_user;

    public OrderModel() {
        super("orders.csv");
    }

    public OrderModel(LocalDateTime date, Float total, int status, String comments, int rating, String products, Long id_user) {
        super("orders.csv");
        this.date = date;
        this.total = total;
        this.status = status;
        this.comments = comments;
        this.rating = rating;
        this.products = products;
        this.id_user = id_user;
    }
}
