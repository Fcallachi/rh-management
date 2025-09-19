package com.rhmanagement.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequestDTO {

    private String name;

    private String cargo;

    private Double salario;

    private Boolean statusPagamento;

}
