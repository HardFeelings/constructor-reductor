package ru.vpt.constructorapp.api.commercial.manager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ManagerApi {
    @GetMapping("/security/manager")
    ResponseEntity<ResponseDto<List<ManagerDto>>> getAll();

    @GetMapping("/security/manager/{id}")
    ResponseEntity<ResponseDto<ManagerDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/manager")
    ResponseEntity<ResponseDto<ManagerDto>> save(@RequestBody ManagerDto managerDto);

    @DeleteMapping("/security/manager/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
