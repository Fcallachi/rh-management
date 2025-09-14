package com.rhmanagement.dtos;

import lombok.Data;

@Data
public class EmployeeRequestDTO {

    private String name;

    private String cargo;

    private Double salario;

    private Boolean statusPagamento;

}
