package dev.tiagosilva.dto;

import java.time.LocalDateTime;

public class OrderRequestDTO {
    private Long id;
    private LocalDateTime date;
    private Float total;
    private int status;
    private String comments;
    private int rating;
    private String products;
    private Long id_user;
}
