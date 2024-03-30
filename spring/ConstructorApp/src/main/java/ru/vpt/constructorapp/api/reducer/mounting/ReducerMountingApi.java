package ru.vpt.constructorapp.api.reducer.mounting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.reducer.mounting.dto.ReducerMountingDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/reducerMounting")
public interface ReducerMountingApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerMountingDto>>> getAllReducerMounting();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerMountingDto>> getById(@PathVariable("id") Long id);
}
