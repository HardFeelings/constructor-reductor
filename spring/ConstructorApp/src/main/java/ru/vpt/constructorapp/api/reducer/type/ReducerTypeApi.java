package ru.vpt.constructorapp.api.reducer.type;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/reducerType")
public interface ReducerTypeApi {

    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerTypeDto>>> getAllReducerTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerTypeDto>> getById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<ReducerTypeDto>> save(@RequestBody ReducerTypeDto reducerTypeDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
