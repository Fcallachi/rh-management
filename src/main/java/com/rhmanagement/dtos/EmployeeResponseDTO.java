package com.rhmanagement.dtos;

import lombok.Data;

@Data
public class EmployeeResponseDTO {

    private Long id;

    private String name;

    private String cargo;

    private Double salario;

    private Boolean statusPagamento;

}
