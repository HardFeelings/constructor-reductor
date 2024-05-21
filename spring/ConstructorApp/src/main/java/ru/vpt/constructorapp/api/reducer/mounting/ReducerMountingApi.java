package ru.vpt.constructorapp.api.reducer.mounting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.mounting.dto.ReducerMountingDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ReducerMountingApi {
    @GetMapping("/reducerMounting")
    ResponseEntity<ResponseDto<List<ReducerMountingDto>>> getAllReducerMounting();

    @GetMapping("/reducerMounting/{id}")
    ResponseEntity<ResponseDto<ReducerMountingDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/reducerMounting")
    ResponseEntity<ResponseDto<ReducerMountingDto>> save(@RequestBody ReducerMountingDto reducerMountingDto);

    @DeleteMapping("/security/reducerMounting/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
