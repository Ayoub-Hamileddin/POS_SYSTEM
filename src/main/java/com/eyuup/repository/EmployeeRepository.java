package com.eyuup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyuup.modal.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByBranchId(Long id);
}
