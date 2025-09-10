package dev.tiagosilva.dto;

import dev.tiagosilva.controller.Controller;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentResponseDTO {
    private Long id;
    private String name;
    private String number;
    private int month;
    private int year;
}
