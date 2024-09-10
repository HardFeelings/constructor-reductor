package ru.vpt.constructorapp.api.commercial.manager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RequestMapping
public interface ManagerApi {
    @GetMapping("/security/manager")
    ResponseEntity<ResponseDto<ManagerPaginationDto>> getAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                             @RequestParam(value = "limit", defaultValue = "20") Integer limit);

    @GetMapping("/security/manager/{id}")
    ResponseEntity<ResponseDto<ManagerDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/manager")
    ResponseEntity<ResponseDto<ManagerDto>> save(@RequestBody ManagerDto managerDto);

    @DeleteMapping("/security/manager/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
