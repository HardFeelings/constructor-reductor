package ru.vpt.constructorapp.api.reducer.output;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/reducerOutputShaftType")
public interface ReducerOutputShaftTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerOutputShaftTypeDto>>> getAllReducerOutputShaftTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerOutputShaftTypeDto>> getById(@PathVariable("id") Long id);
}
