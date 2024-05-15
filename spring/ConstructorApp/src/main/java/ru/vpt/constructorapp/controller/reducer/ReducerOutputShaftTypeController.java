package ru.vpt.constructorapp.controller.reducer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.reducer.output.ReducerOutputShaftTypeApi;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.reducer.ReducerOutputShaftTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReducerOutputShaftTypeController extends AbstractController implements ReducerOutputShaftTypeApi {

    private final ReducerOutputShaftTypeService reducerOutputShaftTypeService;

    @Override
    public ResponseEntity<ResponseDto<List<ReducerOutputShaftTypeDto>>> getAllReducerOutputShaftTypes() {
        return response(reducerOutputShaftTypeService.getAllReducerOutputShaftTypes());
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerOutputShaftTypeDto>> getById(Long id) {
        return response(reducerOutputShaftTypeService.getReducerOutputShaftById(id));
    }
    @Override
    public ResponseEntity<ResponseDto<List<ReducerOutputShaftTypeDto>>> getByReducerTypeId(Long id) {
        return response(reducerOutputShaftTypeService.getAllReducerOutputShaftTypesByReducerTypeId(id));
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerOutputShaftTypeDto>> save(ReducerOutputShaftTypeDto reducerOutputShaftTypeDto) {
        return response(reducerOutputShaftTypeService.saveReducerOutputShaftTypes(reducerOutputShaftTypeDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(reducerOutputShaftTypeService.deleteReducerOutputShaftTypes(id));
    }
}
