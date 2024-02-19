package ru.vpt.constructorapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.reducer.type.ReducerTypeApi;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReducerTypeController extends AbstractResponseController implements ReducerTypeApi {
    @Override
    public ResponseEntity<ResponseDto<List<ReducerTypeDto>>> getAllReducerTypes() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerTypeDto>> getById(Long id) {
        return null;
    }
}
