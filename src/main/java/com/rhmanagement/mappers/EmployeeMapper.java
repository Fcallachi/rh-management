package com.rhmanagement.mappers;

import com.rhmanagement.dtos.EmployeeRequestDTO;
import com.rhmanagement.dtos.EmployeeResponseDTO;
import com.rhmanagement.domains.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
//todo limao
    public Employee convertToEntity(final EmployeeRequestDTO dto) {
        return Employee.builder()
                .name(dto.getName())
                .cargo(dto.getCargo())
                .salario(dto.getSalario())
                .statusPagamento(dto.getStatusPagamento())
                .build();
    }

    //todo limao
    public EmployeeResponseDTO convertToResponseDTO(final Employee employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .cargo(employee.getCargo())
                .salario(employee.getSalario())
                .statusPagamento(employee.getStatusPagamento())
                .build();
    }

}
