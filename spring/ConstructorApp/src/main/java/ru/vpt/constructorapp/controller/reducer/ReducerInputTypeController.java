package ru.vpt.constructorapp.controller.reducer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.reducer.input.ReducerInputTypeApi;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.reducer.ReducerInputTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReducerInputTypeController extends AbstractController implements ReducerInputTypeApi {

    private final ReducerInputTypeService reducerInputTypeService;

    @Override
    public ResponseEntity<ResponseDto<List<ReducerInputTypeDto>>> getAllReducerInputTypes() {
        return response(reducerInputTypeService.getAllReducerInputTypes());
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerInputTypeDto>> getById(Long id) {
        return response(reducerInputTypeService.getReducerInputById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<List<ReducerInputTypeDto>>> getByReducerTypeId(Long id) {
        return response(reducerInputTypeService.getAllReducerInputTypesByReducerTypeId(id));
    }
}
