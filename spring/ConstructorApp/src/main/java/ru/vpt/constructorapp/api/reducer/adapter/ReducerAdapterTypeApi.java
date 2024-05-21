package ru.vpt.constructorapp.api.reducer.adapter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ReducerAdapterTypeApi {
    @GetMapping("/reducerAdapterType")
    ResponseEntity<ResponseDto<List<ReducerAdapterTypeDto>>> getAllReducerAdapterTypes();

    @GetMapping("/reducerAdapterType/{id}")
    ResponseEntity<ResponseDto<ReducerAdapterTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/reducerAdapterType/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerAdapterTypeDto>>> getByReducerTypeId(@PathVariable("id") Long id);

    @PostMapping("/security/reducerAdapterType")
    ResponseEntity<ResponseDto<ReducerAdapterTypeDto>> save(@RequestBody ReducerAdapterTypeDto reducerAdapterTypeDto);

    @DeleteMapping("/security/reducerAdapterType/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
