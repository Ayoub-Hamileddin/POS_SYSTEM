package com.eyuup.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eyuup.mapper.EmployeeMapper;
import com.eyuup.modal.Branch;
import com.eyuup.modal.Employee;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.EmployeeDto;
import com.eyuup.repository.BranchRepository;
import com.eyuup.repository.EmployeeRepository;
import com.eyuup.repository.UserRepository;
import com.eyuup.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final BranchRepository branchRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) throws Exception {

        Branch branch=branchRepository.findById(employeeDto.getBranchId()).orElseThrow(
            ()->new Exception("branch not found")
        );

        User newUser= new User();
            newUser.setFullName(employeeDto.getFullName());
            newUser.setEmail(employeeDto.getEmail());
            newUser.setPhone(employeeDto.getPhone());
            newUser.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
            newUser.setRole(employeeDto.getPosition());
            newUser.setCreatedAt(employeeDto.getCreatedAt());

        userRepository.save(newUser);

        Employee employee=new Employee();
                employee.setFullName(employeeDto.getFullName());
                employee.setEmail(employeeDto.getEmail());
                employee.setPhone(employeeDto.getPhone());
                employee.setPosition(employeeDto.getPosition());
                employee.setUser(newUser);
                employee.setBranch(branch);
                employee.setSalary(employeeDto.getSalary());
                employee.setStatus(employeeDto.getStatus());
                employee.setCreatedAt(employeeDto.getCreatedAt());
                employee.setUpdatedAt(employeeDto.getUpdatedAt());

        employeeRepository.save(employee);





        return EmployeeMapper.toDTO(employee) ;
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) throws Exception {

        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
            ()->new Exception("employee not found")
        );

        if (employeeDto.getSalary()!=null) {employee.setSalary(employeeDto.getSalary());}

        if (employeeDto.getStatus()!=null) {employee.setStatus(employeeDto.getStatus());}

        employee.setUpdatedAt(LocalDateTime.now());
        
        employeeRepository.save(employee);

        return EmployeeMapper.toDTO(employee);

    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
            ()->new Exception("employee not found")
        );

        employeeRepository.delete(employee);

        userRepository.delete(employee.getUser());

    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees=employeeRepository.findAll();

        return employees.stream()
            .map(EmployeeMapper::toDTO)
            .toList();
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) throws Exception {

        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
            ()->new Exception("employee not found")
        );

        return EmployeeMapper.toDTO(employee);
    }

    @Override
    public List<EmployeeDto> getEmployeeByBranchId(Long branchId) {
        List<Employee> employees=employeeRepository.findByBranchId(branchId);

        return employees.stream()
            .map(EmployeeMapper::toDTO)
            .toList();
    }

}
