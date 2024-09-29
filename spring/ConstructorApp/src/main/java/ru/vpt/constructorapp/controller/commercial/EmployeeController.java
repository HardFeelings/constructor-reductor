package ru.vpt.constructorapp.controller.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.commercial.employee.EmployeeApi;
import ru.vpt.constructorapp.api.commercial.employee.dto.EmployeeDto;
import ru.vpt.constructorapp.api.commercial.employee.dto.EmployeePaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.commercial.EmployeeService;

@RestController
@RequiredArgsConstructor
public class EmployeeController extends AbstractController implements EmployeeApi {

    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<ResponseDto<EmployeePaginationDto>> getAll(Integer offset, Integer limit) {
        return response(employeeService.getAll(offset, limit));
    }

    @Override
    public ResponseEntity<ResponseDto<EmployeeDto>> save(EmployeeDto employeeDto) {
        return response(employeeService.save(employeeDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(employeeService.delete(id));
    }
}
