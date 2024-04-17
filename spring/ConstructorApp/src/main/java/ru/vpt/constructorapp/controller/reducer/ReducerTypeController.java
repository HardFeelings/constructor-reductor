package ru.vpt.constructorapp.controller.reducer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.reducer.type.ReducerTypeApi;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.reducer.ReducerTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReducerTypeController extends AbstractController implements ReducerTypeApi {

    private final ReducerTypeService reducerTypeService;

    @Override
    public ResponseEntity<ResponseDto<List<ReducerTypeDto>>> getAllReducerTypes() {
        return response(reducerTypeService.getAllReducerTypes());
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerTypeDto>> getById(Long id) {
        return response(reducerTypeService.getReducerTypeById(id));
    }
}
