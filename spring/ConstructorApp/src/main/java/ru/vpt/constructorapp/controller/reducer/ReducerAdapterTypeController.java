package ru.vpt.constructorapp.controller.reducer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.reducer.adapter.ReducerAdapterTypeApi;
import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.reducer.ReducerAdapterTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReducerAdapterTypeController extends AbstractController implements ReducerAdapterTypeApi {

    private final ReducerAdapterTypeService reducerAdapterTypeService;

    @Override
    public ResponseEntity<ResponseDto<List<ReducerAdapterTypeDto>>> getAllReducerAdapterTypes() {
        return response(reducerAdapterTypeService.getAllReducerAdapterTypes());
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerAdapterTypeDto>> getById(Long id) {
        return response(reducerAdapterTypeService.getReducerAdapterById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<List<ReducerAdapterTypeDto>>> getByReducerTypeId(Long id) {
        return response(reducerAdapterTypeService.getAllReducerAdapterTypesByReducerTypeId(id));
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerAdapterTypeDto>> save(ReducerAdapterTypeDto reducerAdapterTypeDto) {
        return response(reducerAdapterTypeService.saveReducerAdapterType(reducerAdapterTypeDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(reducerAdapterTypeService.deleteReducerAdapterType(id));
    }
}
