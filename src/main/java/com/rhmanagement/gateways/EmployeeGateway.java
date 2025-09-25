package com.rhmanagement.gateways;

import com.rhmanagement.dtos.EmployeeRequestDTO;
import com.rhmanagement.dtos.EmployeeResponseDTO;
import com.rhmanagement.mappers.EmployeeMapper;
import com.rhmanagement.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeGateway {
//TODO TIRAR TODOS OS COMMENTARIOS
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

//  todo retorna uma lista de funcionarios
    public List<EmployeeResponseDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::convertToResponseDTO)
                .toList();
    }

    public Optional<EmployeeResponseDTO> findById(final Long id) {//todo paulo
        return employeeRepository.findById(id)
                .map(employeeMapper::convertToResponseDTO);
    }

    public EmployeeResponseDTO save(final EmployeeRequestDTO employeeRequestDTO) {// todo paulo
        final var employee = employeeMapper.convertToEntity(employeeRequestDTO);
        final var savedEmployee = employeeRepository.save(employee);
        return employeeMapper.convertToResponseDTO(savedEmployee);
    }

    public EmployeeResponseDTO update(final Long id, final EmployeeRequestDTO employeeRequestDTO) {
        final var existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            final var employee = existingEmployee.get();
            employee.setName(employeeRequestDTO.getName());
            employee.setCargo(employeeRequestDTO.getCargo());
            employee.setSalario(employeeRequestDTO.getSalario());
            employee.setStatusPagamento(employeeRequestDTO.getStatusPagamento());
            final var updatedEmployee = employeeRepository.save(employee);
            return employeeMapper.convertToResponseDTO(updatedEmployee);
        }
        throw new RuntimeException("Employee not found with id: " + id);
    }

    public void deleteById(final Long id) {
        employeeRepository.deleteById(id);
    }

}
