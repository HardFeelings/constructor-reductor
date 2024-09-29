package ru.vpt.constructorapp.api.reducer.installation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationPaginationDto;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ReducerInstallationTypeApi {
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reducerInstallationType")
    ResponseEntity<ResponseDto<ReducerInstallationPaginationDto>> getAllReducerInstallationTypes(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                                                   @RequestParam(value = "limit", defaultValue = "15") Integer limit);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reducerInstallationType/{id}")
    ResponseEntity<ResponseDto<ReducerInstallationTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/reducerInstallationType/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerInstallationTypeDto>>> getByReducerTypeId(@PathVariable("id") Long id);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/reducerInstallationType")
    ResponseEntity<ResponseDto<ReducerInstallationTypeDto>> save(@RequestBody ReducerInstallationTypeDto reducerInstallationTypeDto);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/security/reducerInstallationType/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
