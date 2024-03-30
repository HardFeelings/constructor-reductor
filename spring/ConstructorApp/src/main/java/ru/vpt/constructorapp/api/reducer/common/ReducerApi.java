package ru.vpt.constructorapp.api.reducer.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/reducer")
public interface ReducerApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerDto>>> getAllReducerAdapterTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerDto>> getById(@PathVariable("id") Long id);
}
