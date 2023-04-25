package com.junit.testing.Mapper;

import com.junit.testing.DTO.EmployeeDTO;
import com.junit.testing.Entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings({
            @Mapping(target = "firstName", source = "employeeDTO.paternalName"),
            @Mapping(target = "lastName", source = "employeeDTO.maternalName"),
            @Mapping(target = "entryDate", source = "employeeDTO.entryDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    })
    EmployeeEntity employeeDTOToEmployee(EmployeeDTO employeeDTO);

    @Mappings({
            @Mapping(target = "paternalName", source = "employeeEntity.firstName"),
            @Mapping(target = "maternalName", source = "employeeEntity.lastName"),
            @Mapping(target = "entryDate", source = "employeeEntity.entryDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    })
    EmployeeDTO employeeToEmployeeDTO(EmployeeEntity employeeEntity);
}
