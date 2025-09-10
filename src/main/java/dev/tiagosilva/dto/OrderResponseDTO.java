package dev.tiagosilva.dto;

import dev.tiagosilva.controller.Controller;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime date;
    private Float total;
    private int status;
    private String comments;
    private int rating;
    private String products;
    private Long id_user;
}
