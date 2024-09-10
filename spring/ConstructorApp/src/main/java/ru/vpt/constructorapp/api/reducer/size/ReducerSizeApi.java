package ru.vpt.constructorapp.api.reducer.size;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizePaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ReducerSizeApi {
    @GetMapping("/reducerSize")
    ResponseEntity<ResponseDto<ReducerSizePaginationDto>> getAllReducerSizes(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                             @RequestParam(value = "limit", defaultValue = "20") Integer limit);

    @GetMapping("/reducerSize/{id}")
    ResponseEntity<ResponseDto<ReducerSizeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/reducerSize/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerSizeDto>>> getByReducerTypeId(@PathVariable("id") Long id);

    @PostMapping("/security/reducerSize")
    ResponseEntity<ResponseDto<ReducerSizeDto>> save(@RequestBody ReducerSizeDto reducerSizeDto);

    @DeleteMapping("/security/reducerSize/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
