package ru.vpt.constructorapp.api.reducer.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RequestMapping
public interface ReducerApi {
    @GetMapping("/reducer")
    ResponseEntity<ResponseDto<ReducerPaginationDto>> getAllReducerAdapterTypes(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                                @RequestParam(value = "limit", defaultValue = "20") Integer limit);

    @GetMapping("/reducer/{id}")
    ResponseEntity<ResponseDto<ReducerDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/reducer")
    ResponseEntity<ResponseDto<ReducerDto>> save(@RequestBody ReducerDto reducerDto);

    @DeleteMapping("/security/reducer/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
