package ru.vpt.constructorapp.api.reducer.output;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftPaginationDto;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ReducerOutputShaftTypeApi {
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reducerOutputShaftType")
    ResponseEntity<ResponseDto<ReducerOutputShaftPaginationDto>> getAllReducerOutputShaftTypes(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                                               @RequestParam(value = "limit", defaultValue = "15") Integer limit);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reducerOutputShaftType/{id}")
    ResponseEntity<ResponseDto<ReducerOutputShaftTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/reducerOutputShaftType/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerOutputShaftTypeDto>>> getByReducerTypeId(@PathVariable("id") Long id);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/reducerOutputShaftType")
    ResponseEntity<ResponseDto<ReducerOutputShaftTypeDto>> save(@RequestBody ReducerOutputShaftTypeDto reducerOutputShaftTypeDto);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/security/reducerOutputShaftType/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
