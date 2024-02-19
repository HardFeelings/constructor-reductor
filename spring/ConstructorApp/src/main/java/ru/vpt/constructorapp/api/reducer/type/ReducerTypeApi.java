package ru.vpt.constructorapp.api.reducer.type;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/reducerType")
public interface ReducerTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerTypeDto>>> getAllReducerTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerTypeDto>> getById(@PathVariable Long id);
}
