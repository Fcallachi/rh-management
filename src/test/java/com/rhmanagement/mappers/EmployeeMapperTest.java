package com.rhmanagement.mappers;

import com.rhmanagement.domains.Employee;
import com.rhmanagement.dtos.EmployeeResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class EmployeeMapperTest {

    private Employee existEmployee;
    private EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp(){
        employeeMapper = new EmployeeMapper();

        existEmployee = Employee.builder()
                .id(1L)
                .name("Nome1")
                .cargo("Analista Jr")
                .salario(2500.00)
                .statusPagamento(true)
                .build();
    }

    @Test
    public void convertToEntityTest() {
        Employee dto = existEmployee;

        assertEquals(dto.getName(), existEmployee.getName());
        assertEquals(dto.getCargo(),existEmployee.getCargo());
        assertEquals(dto.getSalario(),existEmployee.getSalario());
        assertEquals(dto.getStatusPagamento(),existEmployee.getStatusPagamento());
    }

    @Test
    public void convertToResponseDTOTest() {
        EmployeeResponseDTO responseDTO = employeeMapper.convertToResponseDTO(existEmployee);

        assertEquals(existEmployee.getName(),responseDTO.getName());
        assertEquals(existEmployee.getCargo(),responseDTO.getCargo());
        assertEquals(existEmployee.getSalario(),responseDTO.getSalario());
        assertEquals(existEmployee.getStatusPagamento(),responseDTO.getStatusPagamento());
    }
}