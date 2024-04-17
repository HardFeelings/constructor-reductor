package ru.vpt.constructorapp.controller.reducer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.reducer.common.ReducerApi;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.reducer.ReducerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReducerController extends AbstractController implements ReducerApi {

    private final ReducerService reducerService;
    @Override
    public ResponseEntity<ResponseDto<List<ReducerDto>>> getAllReducerAdapterTypes() {
        return response(reducerService.getAllReducer());
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerDto>> getById(Long id) {
        return response(reducerService.getReducerById(id));
    }
}
