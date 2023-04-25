package com.junit.testing.Mapper;

import com.junit.testing.DTO.EmployeeDTO;
import com.junit.testing.Entity.EmployeeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeMapperTest {

    private EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @DisplayName("Should mapping a employee entity to employee DTO class")
    @Test
    public void employeeSimpleMapperTest(){
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(1L);
        employee.setFirstName("Fonza");
        employee.setLastName("Rex");
        employee.setEmail("emailfake@email.com");
        employee.setEntryDate(new Date());

        EmployeeDTO employeeDTO = mapper.employeeToEmployeeDTO(employee);

        System.out.println(employeeDTO);
        System.out.println(employee);
        assertEquals(employeeDTO.getId(), employee.getId());
    }
}