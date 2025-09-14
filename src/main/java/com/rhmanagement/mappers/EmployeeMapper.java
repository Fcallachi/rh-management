package com.rhmanagement.mappers;

import com.rhmanagement.dtos.EmployeeRequestDTO;
import com.rhmanagement.dtos.EmployeeResponseDTO;
import com.rhmanagement.domains.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee convertToEntity(final EmployeeRequestDTO dto) {
        final var employee = new Employee();
        employee.setName(dto.getName());
        employee.setCargo(dto.getCargo());
        employee.setSalario(dto.getSalario());
        employee.setStatusPagamento(dto.getStatusPagamento());
        return employee;
    }

    public EmployeeResponseDTO convertToResponseDTO(final Employee employee) {
        final var dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setCargo(employee.getCargo());
        dto.setSalario(employee.getSalario());
        dto.setStatusPagamento(employee.getStatusPagamento());
        return dto;
    }

}
