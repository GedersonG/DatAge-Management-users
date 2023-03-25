package com.junit.testing.ServiceImp;

import com.junit.testing.Entity.EmployeeEntity;
import com.junit.testing.Exception.ResourceNotFoundException;
import com.junit.testing.Repository.EmployeeRepository;
import com.junit.testing.Service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity employee) throws ResourceNotFoundException {
        Optional<EmployeeEntity> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee already exist with given email:" + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return null;
    }

    @Override
    public Optional<EmployeeEntity> getEmployeeById(long id) {
        return Optional.empty();
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity updateEmployee) {
        return null;
    }

    @Override
    public void deleteEmployee(long id) {

    }
}
