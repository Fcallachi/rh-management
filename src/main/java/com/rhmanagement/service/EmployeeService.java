package com.rhmanagement.service;

import com.rhmanagement.model.Employee;
import com.rhmanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(final Long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(final Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(final Long id, final Employee employee) {
        final var existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            final var updatedEmployee = existingEmployee.get();
            updatedEmployee.setName(employee.getName());
            updatedEmployee.setCargo(employee.getCargo());
            updatedEmployee.setSalario(employee.getSalario());
            updatedEmployee.setStatusPagamento(employee.getStatusPagamento());
            return employeeRepository.save(updatedEmployee);
        }
        throw new RuntimeException("Employee not found with id: " + id);
    }

    public void deleteById(final Long id) {
        employeeRepository.deleteById(id);
    }

}
