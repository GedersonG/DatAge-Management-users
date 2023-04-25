package com.junit.testing.ServiceImp;

import com.junit.testing.Entity.EmployeeEntity;
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
    public EmployeeEntity saveEmployee(EmployeeEntity employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Optional<EmployeeEntity> getEmployeeById(long id){
        return this.employeeRepository.findById(id);
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