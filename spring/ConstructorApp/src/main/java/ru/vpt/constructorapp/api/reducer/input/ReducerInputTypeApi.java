package ru.vpt.constructorapp.api.reducer.input;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/reducerInputType")
public interface ReducerInputTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerInputTypeDto>>> getAllReducerInputTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerInputTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerInputTypeDto>>> getByReducerTypeId(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<ReducerInputTypeDto>> save(@RequestBody ReducerInputTypeDto reducerInputTypeDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
