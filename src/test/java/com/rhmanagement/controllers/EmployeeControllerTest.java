package com.rhmanagement.controllers;

import com.rhmanagement.dtos.EmployeeRequestDTO;
import com.rhmanagement.dtos.EmployeeResponseDTO;
import com.rhmanagement.gateways.EmployeeGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeGateway employeeGateway;

    @InjectMocks
    private EmployeeController employeeController;

    //todo padrao de nome de teste ( o esperado que ocorra no teste, quando vai ocorrer)
    @Test
    void shouldReturnOkWithUpdated_WhenEmployeeExists() {
        final var employeeId = 1L;
        final var employeeRequestDTO = EmployeeRequestDTO.builder()
                .name("João Silva")
                .cargo("Desenvolvedor")
                .salario(5000.0)
                .statusPagamento(true)
                .build();

        final var employeeResponseDTO = EmployeeResponseDTO.builder()
                .id(employeeId)
                .name("João Silva")
                .cargo("Desenvolvedor")
                .salario(5000.0)
                .statusPagamento(true)
                .build();

        when(employeeGateway.update(eq(employeeId), any(EmployeeRequestDTO.class)))
                .thenReturn(employeeResponseDTO);

        final var response = employeeController.updateEmployee(employeeId, employeeRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(employeeId, response.getBody().getId());
        assertEquals("João Silva", response.getBody().getName());
        assertEquals("Desenvolvedor", response.getBody().getCargo());
        assertEquals(5000.0, response.getBody().getSalario());
        assertEquals(true, response.getBody().getStatusPagamento());
    }

    @Test
    void shouldReturnNotFound_WhenEmployeeNotFound() {
        final var employeeId = 999L;
        final var employeeRequestDTO = EmployeeRequestDTO.builder()
                .name("João Silva")
                .cargo("Desenvolvedor")
                .salario(5000.0)
                .statusPagamento(true)
                .build();

        doThrow(new RuntimeException("Employee not found with id: " + employeeId))
                .when(employeeGateway).update(eq(employeeId), any(EmployeeRequestDTO.class));

        final var response = employeeController.updateEmployee(employeeId, employeeRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}