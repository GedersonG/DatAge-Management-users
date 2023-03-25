package com.junit.testing.ServiceImp;

import com.junit.testing.Entity.EmployeeEntity;
import com.junit.testing.Exception.ResourceNotFoundException;
import com.junit.testing.Repository.EmployeeRepository;
import com.junit.testing.Service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity employee) throws ResourceNotFoundException {
        Optional<EmployeeEntity> savedEmployee = this.employeeRepository.findByEmail(employee.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee already exist with given email: " + employee.getEmail());
        }
        return this.employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity getEmployeeById(long id) throws ResourceNotFoundException {
        Optional<EmployeeEntity> employee = this.employeeRepository.findById(id);
        if(!employee.isPresent()){
            throw new ResourceNotFoundException("Employee with id " + id + " is not found.");
        }
        return employee.get();
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity updateEmployee) {
        return this.employeeRepository.save(updateEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        this.employeeRepository.deleteById(id);
    }
}