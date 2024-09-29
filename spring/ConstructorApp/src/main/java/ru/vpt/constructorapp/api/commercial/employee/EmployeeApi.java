package ru.vpt.constructorapp.api.commercial.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.commercial.employee.dto.EmployeeDto;
import ru.vpt.constructorapp.api.commercial.employee.dto.EmployeePaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RequestMapping
public interface EmployeeApi {
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/security/admin/employee")
    ResponseEntity<ResponseDto<EmployeePaginationDto>> getAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                              @RequestParam(value = "limit", defaultValue = "15") Integer limit);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/admin/employee")
    ResponseEntity<ResponseDto<EmployeeDto>> save(@RequestBody EmployeeDto employeeDto);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/security/admin/employee/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
