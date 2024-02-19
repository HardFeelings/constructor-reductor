package ru.vpt.constructorapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.reducer.common.ReducerApi;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.service.ReducerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReducerController extends AbstractResponseController implements ReducerApi {
    private final ReducerService reducerService;
    @Override
    public ResponseEntity<ResponseDto<List<ReducerDto>>> getAllReducer() {
        return response(reducerService.getAllReducer());
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerDto>> getById(Long id) {
        return response(reducerService.getById(id));
    }
}
