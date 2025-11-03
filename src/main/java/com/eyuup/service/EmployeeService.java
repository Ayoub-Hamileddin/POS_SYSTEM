package com.eyuup.service;

import java.util.List;

import com.eyuup.payload.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Long employeeId,EmployeeDto employeeDto);
    EmployeeDto deleteEmployee(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getEmployeeByBranchId(Long branchId);
}
