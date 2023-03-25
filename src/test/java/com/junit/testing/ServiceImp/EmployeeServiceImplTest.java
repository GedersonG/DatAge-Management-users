package com.junit.testing.ServiceImp;

import com.junit.testing.Entity.EmployeeEntity;
import com.junit.testing.Exception.ResourceNotFoundException;
import com.junit.testing.Repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private EmployeeEntity employee;

    @BeforeEach
    public void setup(){
        employee = EmployeeEntity.builder()
                .id(1L)
                .firstName("Gederson")
                .lastName("Guzman")
                .email("gederkaselber@gmail.com")
                .build();
    }

    @DisplayName("JUnit test for saveEmployee method")
    @Test
    void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() throws ResourceNotFoundException {
        // GIVEN
        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);

        // WHEN
        EmployeeEntity savedEmployee = employeeService.saveEmployee(employee);

        // THEN
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("JUnit test for saveEmployee method which throws exception")
    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException() {
        // GIVEN
        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

        // WHEN
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.saveEmployee(employee);
        });

        // THEN
        verify(employeeRepository, never()).save(any(EmployeeEntity.class));
    }

    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        // GIVEN
        EmployeeEntity otherEmployee = EmployeeEntity.builder()
                .id(2L)
                .firstName("Mario")
                .lastName("Neta")
                .email("marioneta@gmail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(List.of(employee,otherEmployee));

        // WHEN
        List<EmployeeEntity> employeeList = employeeService.getAllEmployees();

        // THEN
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getAllEmployees method (negative scenario)")
    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList(){
        // GIVEN
        EmployeeEntity otherEmployee = EmployeeEntity.builder()
                .id(2L)
                .firstName("Mario")
                .lastName("Neta")
                .email("marioneta@gmail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        // WHEN
        List<EmployeeEntity> employeeList = employeeService.getAllEmployees();

        // THEN
        assertThat(employeeList).isEmpty();
        assertThat(employeeList.size()).isEqualTo(0);
    }

    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws ResourceNotFoundException {
        // GIVEN
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        // WHEN
        EmployeeEntity employeeEntity = employeeService.getEmployeeById(1L);

        // THEN
        assertThat(employeeEntity).isNotNull();
    }

    @DisplayName("JUnit test for getEmployeeById method which throws exception")
    @Test
    public void givenNotExistingId_whenGetEmployeeById_thenThrowsException() {
        // GIVEN
        given(employeeRepository.findById(1L)).willReturn(Optional.empty());

        // WHEN
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById(1L);
        });

        // THEN
        verify(employeeRepository, never()).getReferenceById(1L);
    }

    @DisplayName("JUnit test for updateEmployee method")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // GIVEN
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setFirstName("Fonza");
        employee.setLastName("Jr.");
        employee.setEmail("gederson@gmail.com");

        // WHEN
        EmployeeEntity updatedEmployee = employeeService.updateEmployee(employee);

        // THEN
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Fonza");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Jr.");
        assertThat(updatedEmployee.getEmail()).isEqualTo("gederson@gmail.com");
    }

    @DisplayName("JUnit test for deleteEmployee method")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenNothing(){
        // GIVEN
        long id = 1L;
        willDoNothing().given(employeeRepository).deleteById(id);

        // WHEN
        employeeService.deleteEmployee(id);

        // THEN
        verify(employeeRepository, times(1)).deleteById(id);
    }
}