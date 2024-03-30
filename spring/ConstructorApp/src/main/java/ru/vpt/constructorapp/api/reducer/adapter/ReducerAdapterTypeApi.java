package ru.vpt.constructorapp.api.reducer.adapter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/reducerAdapterType")
public interface ReducerAdapterTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerAdapterTypeDto>>> getAllReducerAdapterTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerAdapterTypeDto>> getById(@PathVariable("id") Long id);
}
