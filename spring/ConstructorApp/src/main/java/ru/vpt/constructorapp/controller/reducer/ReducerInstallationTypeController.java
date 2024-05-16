package ru.vpt.constructorapp.controller.reducer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.reducer.installation.ReducerInstallationTypeApi;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.reducer.ReducerInstallationTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReducerInstallationTypeController extends AbstractController implements ReducerInstallationTypeApi {

    private final ReducerInstallationTypeService reducerInstallationTypeService;

    @Override
    public ResponseEntity<ResponseDto<List<ReducerInstallationTypeDto>>> getAllReducerInstallationTypes() {
        return response(reducerInstallationTypeService.getAllReducerInstallationTypes());
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerInstallationTypeDto>> getById(Long id) {
        return response(reducerInstallationTypeService.getReducerInstallationById(id));
    }
    @Override
    public ResponseEntity<ResponseDto<List<ReducerInstallationTypeDto>>> getByReducerTypeId(Long id) {
        return response(reducerInstallationTypeService.getAllReducerInstallationTypesByReducerTypeId(id));
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerInstallationTypeDto>> save(ReducerInstallationTypeDto reducerInstallationTypeDto) {
        return response(reducerInstallationTypeService.saveReducerInstallationType(reducerInstallationTypeDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(reducerInstallationTypeService.deleteReducerInstallationType(id));
    }
}
