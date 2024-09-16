package ru.vpt.constructorapp.api.reducer.input;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputPaginationDto;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ReducerInputTypeApi {
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reducerInputType")
    ResponseEntity<ResponseDto<ReducerInputPaginationDto>> getAllReducerInputTypes(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                                   @RequestParam(value = "limit", defaultValue = "20") Integer limit);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reducerInputType/{id}")
    ResponseEntity<ResponseDto<ReducerInputTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/reducerInputType/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerInputTypeDto>>> getByReducerTypeId(@PathVariable("id") Long id);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/reducerInputType")
    ResponseEntity<ResponseDto<ReducerInputTypeDto>> save(@RequestBody ReducerInputTypeDto reducerInputTypeDto);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/security/reducerInputType/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
