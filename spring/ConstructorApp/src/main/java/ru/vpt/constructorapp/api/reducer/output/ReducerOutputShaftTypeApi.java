package ru.vpt.constructorapp.api.reducer.output;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/reducerOutputShaftType")
public interface ReducerOutputShaftTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerOutputShaftTypeDto>>> getAllReducerOutputShaftTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerOutputShaftTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerOutputShaftTypeDto>>> getByReducerTypeId(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<ReducerOutputShaftTypeDto>> save(@RequestBody ReducerOutputShaftTypeDto reducerOutputShaftTypeDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
