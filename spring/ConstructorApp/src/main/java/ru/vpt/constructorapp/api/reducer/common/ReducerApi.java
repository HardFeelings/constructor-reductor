package ru.vpt.constructorapp.api.reducer.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/reducer")
public interface ReducerApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerDto>>> getAllReducerAdapterTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerDto>> getById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<ReducerDto>> save(@RequestBody ReducerDto reducerDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
