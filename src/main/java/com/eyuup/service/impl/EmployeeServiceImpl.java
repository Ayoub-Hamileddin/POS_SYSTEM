package com.eyuup.service.impl;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eyuup.domain.CheckAuthority;
import com.eyuup.mapper.EmployeeMapper;
import com.eyuup.modal.Branch;
import com.eyuup.modal.Employee;
import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.EmployeeDto;
import com.eyuup.repository.BranchRepository;
import com.eyuup.repository.EmployeeRepository;
import com.eyuup.repository.StoreRepository;
import com.eyuup.repository.UserRepository;
import com.eyuup.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final BranchRepository branchRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto,User currentUser) throws Exception {

        Branch branch=branchRepository.findById(employeeDto.getBranchId()).orElseThrow(
            ()->new Exception("branch not found")
        );

            // *-------check roles(Admin + Manger )--------
            CheckAuthority.isAuthorized(currentUser,branch.getStore());

        User user= new User();
            user.setFullName(employeeDto.getFullName());
            user.setEmail(employeeDto.getEmail());
            user.setPhone(employeeDto.getPhone());
            user.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
            user.setRole(employeeDto.getPosition());
            user.setCreatedAt(LocalDateTime.now());

       User newUser= userRepository.save(user);

        Employee employee=new Employee();
                employee.setFullName(employeeDto.getFullName());
                employee.setEmail(employeeDto.getEmail());
                employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
                employee.setPhone(employeeDto.getPhone());
                employee.setPosition(employeeDto.getPosition());
                employee.setUser(newUser);
                employee.setBranch(branch);
                employee.setSalary(employeeDto.getSalary());
                employee.setStatus(employeeDto.getStatus());
                employee.setCreatedAt(LocalDateTime.now());
                employee.setUpdatedAt(employeeDto.getUpdatedAt());

        employeeRepository.save(employee);





        return EmployeeMapper.toDTO(employee) ;
    }


    
    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto,User currentUser) throws Exception {


            // *-------check roles(Admin + Manger )--------
            CheckAuthority.isAuthorized(currentUser,currentUser.getStore());

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
    public void deleteEmployee(Long employeeId,User user) throws Exception {

        Store store=storeRepository.findByStoreAdminId(user.getId()).orElseThrow(
            ()->new AccessDeniedException("you're not allowed to delete this employee")
        );

        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
            ()->new Exception("employee not found")
        );

        employeeRepository.delete(employee);

        userRepository.delete(employee.getUser());

    }

    @Override
    public List<EmployeeDto> getAllEmployees(User user) throws Exception {
       
        CheckAuthority.isAuthorized(user, user.getStore());

        List<Employee> employees=employeeRepository.findAll();

        return employees.stream()
            .map(EmployeeMapper::toDTO)
            .toList();
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId,User user) throws Exception {


        CheckAuthority.isAuthorized(user, user.getStore());

        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
            ()->new Exception("employee not found")
        );

        return EmployeeMapper.toDTO(employee);
    }

    @Override
    public List<EmployeeDto> getEmployeeByBranchId(Long branchId,User user) throws Exception {

        CheckAuthority.isAuthorized(user, user.getStore());

        List<Employee> employees=employeeRepository.findByBranchId(branchId);

        return employees.stream()
            .map(EmployeeMapper::toDTO)
            .toList();
    }

}
