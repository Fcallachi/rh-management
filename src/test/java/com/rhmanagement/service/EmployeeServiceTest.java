package com.rhmanagement.service;

import com.rhmanagement.model.Employee;
import com.rhmanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("João Silva");
        employee.setEmail("joao.silva@email.com");
        employee.setPosition("Desenvolvedor");
        employee.setSalary(5000.0);
    }

    @Test
    void findAll_ShouldReturnAllEmployees() {
        final List<Employee> employees = Arrays.asList(employee);
        when(employeeRepository.findAll()).thenReturn(employees);

        final List<Employee> result = employeeService.findAll();

        assertEquals(1, result.size());
        assertEquals(employee.getName(), result.get(0).getName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void findById_WhenEmployeeExists_ShouldReturnEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        final Optional<Employee> result = employeeService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(employee.getName(), result.get().getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void findById_WhenEmployeeNotExists_ShouldReturnEmpty() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        final Optional<Employee> result = employeeService.findById(1L);

        assertFalse(result.isPresent());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void save_ShouldReturnSavedEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        final Employee result = employeeService.save(employee);

        assertEquals(employee.getName(), result.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        employeeService.deleteById(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }
}

