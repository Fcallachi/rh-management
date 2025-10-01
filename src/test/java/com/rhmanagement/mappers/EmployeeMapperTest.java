package com.rhmanagement.mappers;

import com.rhmanagement.domains.Employee;
import com.rhmanagement.dtos.EmployeeResponseDTO;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

class EmployeeMapperTest {
    private final EmployeeMapper employeeMapper = new EmployeeMapper();

    @Test
    public void convertToEntityTest() {
        final var existEmployee = Employee.builder()
                .id(1L)
                .name("Guilherme")
                .cargo("Estagiario")
                .salario(1.00)
                .statusPagamento(true)
                .build();

        assertEquals(existEmployee.getName(), existEmployee.getName());
        assertEquals(existEmployee.getCargo(), existEmployee.getCargo());
        assertEquals(existEmployee.getSalario(), existEmployee.getSalario());
        assertEquals(existEmployee.getStatusPagamento(), existEmployee.getStatusPagamento());
    }

    @Test
    public void convertToResponseDTOTest() {
        final var existEmployee = Employee.builder()
                .id(1L)
                .name("Guilherme")
                .cargo("Estagiario")
                .salario(1.0)
                .statusPagamento(true)
                .build();
        final EmployeeResponseDTO responseDTO = employeeMapper.convertToResponseDTO(existEmployee);

        assertEquals(existEmployee.getName(), responseDTO.getName());
        assertEquals(existEmployee.getCargo(), responseDTO.getCargo());
        assertEquals(existEmployee.getSalario(), responseDTO.getSalario());
        assertEquals(existEmployee.getStatusPagamento(), responseDTO.getStatusPagamento());
    }
}