package com.junit.testing.Service;

import com.junit.testing.Entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeEntity saveEmployee(EmployeeEntity employee);
    List<EmployeeEntity> getAllEmployees();
    Optional<EmployeeEntity> getEmployeeById(long id);
    EmployeeEntity updateEmployee(EmployeeEntity updateEmployee);
    void deleteEmployee(long id);
}
