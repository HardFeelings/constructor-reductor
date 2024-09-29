package ru.vpt.constructorapp.api.reducer.common;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RequestMapping
public interface ReducerApi {
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reducer")
    ResponseEntity<ResponseDto<ReducerPaginationDto>> getAllReducerAdapterTypes(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                                @RequestParam(value = "limit", defaultValue = "15") Integer limit);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reducer/{id}")
    ResponseEntity<ResponseDto<ReducerDto>> getById(@PathVariable("id") Long id);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/reducer")
    ResponseEntity<ResponseDto<ReducerDto>> save(@RequestBody ReducerDto reducerDto);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/security/reducer/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
