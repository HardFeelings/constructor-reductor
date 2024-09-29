package ru.vpt.constructorapp.api.commercial.employee.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.commercial.employee.dto.EmployeeDto;
import ru.vpt.constructorapp.store.entities.commercial.Employee;
import ru.vpt.constructorapp.store.entities.commercial.Role;

import java.util.Collection;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmployeeMapper {

    @Mapping(target = "admin", source = "roles")
    EmployeeDto toDTO(Employee entity);

    @Mapping(target = "roles", ignore = true)
    Employee toEntity(EmployeeDto dto);

    default Boolean toBoolean(Collection<Role> roles){
        for(Role role : roles){
            if(role.getRole().equals("ROLE_ADMIN"))
                return true;
        }
        return false;
    }
}
