package ru.vpt.constructorapp.api.reducer.input;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/reducerInputType")
public interface ReducerInputTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerInputTypeDto>>> getAllReducerInputTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerInputTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerInputTypeDto>>> getByReducerTypeId(@PathVariable("id") Long id);
}
