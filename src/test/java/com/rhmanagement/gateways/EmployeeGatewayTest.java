package com.rhmanagement.gateways;

import com.rhmanagement.domains.Employee;
import com.rhmanagement.dtos.EmployeeResponseDTO;
import com.rhmanagement.mappers.EmployeeMapper;
import com.rhmanagement.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeGatewayTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeGateway employeeGateway;

    @Test
    void shouldReturnAllEmployees_WhenFindAll() {
        final var employees = createEmployees();
        final var expectedResponseDTOs = createEmployeeResponseDTOs();

        mockRepositoryAndMapper(employees, expectedResponseDTOs);

        final var result = employeeGateway.findAll();

        assertEmployeesFound(result, expectedResponseDTOs);
    }

    @Test
    void shouldReturnEmptyList_WhenNoEmployeesFound() {
        when(employeeRepository.findAll()).thenReturn(List.of());

        final var result = employeeGateway.findAll();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    private List<Employee> createEmployees() {
        return Arrays.asList(
                createEmployee(1L, "João Silva", "Desenvolvedor", 5000.0, true),
                createEmployee(2L, "Maria Santos", "Analista", 4500.0, false),
                createEmployee(3L, "Pedro Costa", "Gerente", 8000.0, true)
        );
    }

    private List<EmployeeResponseDTO> createEmployeeResponseDTOs() {
        return Arrays.asList(
                createEmployeeResponseDTO(1L, "João Silva", "Desenvolvedor", 5000.0, true),
                createEmployeeResponseDTO(2L, "Maria Santos", "Analista", 4500.0, false),
                createEmployeeResponseDTO(3L, "Pedro Costa", "Gerente", 8000.0, true)
        );
    }

    private Employee createEmployee(final Long id, final String name, final String cargo, final Double salario, final Boolean statusPagamento) {
        return Employee.builder()
                .id(id)
                .name(name)
                .cargo(cargo)
                .salario(salario)
                .statusPagamento(statusPagamento)
                .build();
    }

    private EmployeeResponseDTO createEmployeeResponseDTO(final Long id, final String name, final String cargo, final Double salario, final Boolean statusPagamento) {
        return EmployeeResponseDTO.builder()
                .id(id)
                .name(name)
                .cargo(cargo)
                .salario(salario)
                .statusPagamento(statusPagamento)
                .build();
    }

    private void mockRepositoryAndMapper(final List<Employee> employees, final List<EmployeeResponseDTO> responseDTOs) {
        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeMapper.convertToResponseDTO(employees.get(0))).thenReturn(responseDTOs.get(0));
        when(employeeMapper.convertToResponseDTO(employees.get(1))).thenReturn(responseDTOs.get(1));
        when(employeeMapper.convertToResponseDTO(employees.get(2))).thenReturn(responseDTOs.get(2));
    }

    private void assertEmployeesFound(final List<EmployeeResponseDTO> result, final List<EmployeeResponseDTO> expectedResponseDTOs) {
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(expectedResponseDTOs.get(0), result.getFirst());
        assertEquals(expectedResponseDTOs.get(1), result.get(1));
        assertEquals(expectedResponseDTOs.get(2), result.get(2));
    }

}