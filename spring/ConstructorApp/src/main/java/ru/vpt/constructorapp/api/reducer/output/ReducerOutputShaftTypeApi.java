package ru.vpt.constructorapp.api.reducer.output;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftPaginationDto;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ReducerOutputShaftTypeApi {
    @GetMapping("/reducerOutputShaftType")
    ResponseEntity<ResponseDto<ReducerOutputShaftPaginationDto>> getAllReducerOutputShaftTypes(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                                               @RequestParam(value = "limit", defaultValue = "20") Integer limit);

    @GetMapping("/reducerOutputShaftType/{id}")
    ResponseEntity<ResponseDto<ReducerOutputShaftTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/reducerOutputShaftType/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerOutputShaftTypeDto>>> getByReducerTypeId(@PathVariable("id") Long id);

    @PostMapping("/security/reducerOutputShaftType")
    ResponseEntity<ResponseDto<ReducerOutputShaftTypeDto>> save(@RequestBody ReducerOutputShaftTypeDto reducerOutputShaftTypeDto);

    @DeleteMapping("/security/reducerOutputShaftType/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
