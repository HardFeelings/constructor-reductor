package ru.vpt.constructorapp.api.reducer.type;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ReducerTypeApi {

    @GetMapping("/reducerType")
    ResponseEntity<ResponseDto<List<ReducerTypeDto>>> getAllReducerTypes();

    @GetMapping("/reducerType/{id}")
    ResponseEntity<ResponseDto<ReducerTypeDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/reducerType")
    ResponseEntity<ResponseDto<ReducerTypeDto>> save(@RequestBody ReducerTypeDto reducerTypeDto);

    @DeleteMapping("/security/reducerType/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
