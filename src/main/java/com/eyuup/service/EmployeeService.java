package com.eyuup.service;

import java.util.List;

import com.eyuup.modal.User;
import com.eyuup.payload.dto.EmployeeDto;

public interface EmployeeService {
    
    EmployeeDto createEmployee(EmployeeDto employeeDto,User user) throws Exception;

    EmployeeDto updateEmployee(Long employeeId,EmployeeDto employeeDto,User user) throws Exception;

    void deleteEmployee(Long employeeId,User user) throws Exception;

    List<EmployeeDto> getAllEmployees(User user) throws Exception;

    EmployeeDto getEmployeeById(Long employeeId,User user) throws Exception;

    List<EmployeeDto> getEmployeeByBranchId(Long branchId,User user) throws Exception;
}
