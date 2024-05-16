package ru.vpt.constructorapp.api.reducer.size;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/reducerSize")
public interface ReducerSizeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerSizeDto>>> getAllReducerSizes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerSizeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerSizeDto>>> getByReducerTypeId(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<ReducerSizeDto>> save(@RequestBody ReducerSizeDto reducerSizeDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
