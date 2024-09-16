package ru.vpt.constructorapp.api.reducer.type;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ReducerTypeApi {

    @GetMapping("/reducerType")
    ResponseEntity<ResponseDto<List<ReducerTypeDto>>> getAllReducerTypes();

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reducerType/{id}")
    ResponseEntity<ResponseDto<ReducerTypeDto>> getById(@PathVariable("id") Long id);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/reducerType")
    ResponseEntity<ResponseDto<ReducerTypeDto>> save(@RequestBody ReducerTypeDto reducerTypeDto);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/security/reducerType/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
