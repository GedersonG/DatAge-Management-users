package com.junit.testing.Controller;

import com.junit.testing.Entity.EmployeeEntity;
import com.junit.testing.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeEntity> saveEmployee(@Validated @RequestBody EmployeeEntity employee){
        EmployeeEntity newEmployee = this.employeeService.saveEmployee(employee);
        return new ResponseEntity(newEmployee, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees(){
        List<EmployeeEntity> employeeList = this.employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("/{id-employee}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id-employee") long id) {
        Optional<EmployeeEntity> employee = this.employeeService.getEmployeeById(id);
        return employee.map(data -> ResponseEntity.ok(data))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
