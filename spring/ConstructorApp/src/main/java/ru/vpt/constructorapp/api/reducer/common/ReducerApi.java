package ru.vpt.constructorapp.api.reducer.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ReducerApi {
    @GetMapping("/reducer")
    ResponseEntity<ResponseDto<List<ReducerDto>>> getAllReducerAdapterTypes();

    @GetMapping("/reducer/{id}")
    ResponseEntity<ResponseDto<ReducerDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/reducer")
    ResponseEntity<ResponseDto<ReducerDto>> save(@RequestBody ReducerDto reducerDto);

    @DeleteMapping("/security/reducer/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
