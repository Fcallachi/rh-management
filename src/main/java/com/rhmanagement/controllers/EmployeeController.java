package com.rhmanagement.controllers;

import com.rhmanagement.dtos.EmployeeRequestDTO;
import com.rhmanagement.dtos.EmployeeResponseDTO;
import com.rhmanagement.gateways.EmployeeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeGateway employeeGateway;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        final var employees = employeeGateway.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable final Long id) {
        final var employee = employeeGateway.findById(id);
        return employee.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody final EmployeeRequestDTO employeeRequestDTO) {
        final var savedEmployee = employeeGateway.save(employeeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable final Long id, @RequestBody final EmployeeRequestDTO employeeRequestDTO) {
        try {
            final var updatedEmployee = employeeGateway.update(id, employeeRequestDTO);
            return ResponseEntity.ok(updatedEmployee);
        } catch (final RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable final Long id) {
        employeeGateway.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

