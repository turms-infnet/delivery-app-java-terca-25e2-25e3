package dev.tiagosilva.dto;

import dev.tiagosilva.controller.Controller;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDTO {
    private Long id;
    private String street;
    private int number;
    private String comp;
    private String neighborhood;
}
