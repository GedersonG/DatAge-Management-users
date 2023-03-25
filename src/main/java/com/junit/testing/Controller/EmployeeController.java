package com.junit.testing.Controller;

import com.junit.testing.Entity.EmployeeEntity;
import com.junit.testing.Exception.ResourceNotFoundException;
import com.junit.testing.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeEntity> saveEmployee(@RequestBody EmployeeEntity employee){
        try{
            EmployeeEntity newEmployee = this.employeeService.saveEmployee(employee);
            return new ResponseEntity(newEmployee, HttpStatus.OK);
        } catch(ResourceNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees(){
        List<EmployeeEntity> employees = this.employeeService.getAllEmployees();
        return new ResponseEntity(employees, HttpStatus.OK);
    }

    @GetMapping("/{id-employee}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id-employee") long id){
        try {
            EmployeeEntity employee = this.employeeService.getEmployeeById(id);
            return new ResponseEntity(employee, HttpStatus.OK);
        } catch(ResourceNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
