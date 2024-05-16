package ru.vpt.constructorapp.api.reducer.adapter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/reducerAdapterType")
public interface ReducerAdapterTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerAdapterTypeDto>>> getAllReducerAdapterTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerAdapterTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerAdapterTypeDto>>> getByReducerTypeId(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<ReducerAdapterTypeDto>> save(@RequestBody ReducerAdapterTypeDto reducerAdapterTypeDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
