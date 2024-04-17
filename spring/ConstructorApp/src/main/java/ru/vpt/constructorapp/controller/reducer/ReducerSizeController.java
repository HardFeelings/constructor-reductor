package ru.vpt.constructorapp.controller.reducer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.reducer.size.ReducerSizeApi;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.reducer.ReducerSizeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReducerSizeController extends AbstractController implements ReducerSizeApi {

    private final ReducerSizeService reducerSizeService;

    @Override
    public ResponseEntity<ResponseDto<List<ReducerSizeDto>>> getAllReducerSizes() {
        return response(reducerSizeService.getAllReducerSizes());
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerSizeDto>> getById(Long id) {
        return response(reducerSizeService.getReducerSizeById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<List<ReducerSizeDto>>> getByReducerTypeId(Long id) {
        return response(reducerSizeService.getAllReducerSizesByReducerTypeId(id));
    }
}
