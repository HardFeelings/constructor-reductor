package ru.vpt.constructorapp.api.commercial.manager;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ManagerApi {
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/security/admin/manager")
    ResponseEntity<ResponseDto<ManagerPaginationDto>> getAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                             @RequestParam(value = "limit", defaultValue = "15") Integer limit);
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/security/admin/manager/{id}")
    ResponseEntity<ResponseDto<ManagerDto>> getById(@PathVariable("id") Long id);
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/admin/manager")
    ResponseEntity<ResponseDto<ManagerDto>> save(@RequestBody ManagerDto managerDto);
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/security/admin/manager/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);

    @GetMapping("/security/manager")
    ResponseEntity<ResponseDto<List<ManagerDto>>> getAllWithoutPagination();
}
