package com.junit.testing.Service;

import com.junit.testing.Entity.EmployeeEntity;
import com.junit.testing.Exception.ResourceNotFoundException;

import java.util.List;

public interface EmployeeService {
    EmployeeEntity saveEmployee(EmployeeEntity employee) throws ResourceNotFoundException;
    List<EmployeeEntity> getAllEmployees();
    EmployeeEntity getEmployeeById(long id) throws ResourceNotFoundException;
    EmployeeEntity updateEmployee(EmployeeEntity updateEmployee);
    void deleteEmployee(long id);
}
