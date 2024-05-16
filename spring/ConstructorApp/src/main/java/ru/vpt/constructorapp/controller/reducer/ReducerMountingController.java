package ru.vpt.constructorapp.controller.reducer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.reducer.mounting.ReducerMountingApi;
import ru.vpt.constructorapp.api.reducer.mounting.dto.ReducerMountingDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.reducer.ReducerMountingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReducerMountingController extends AbstractController implements ReducerMountingApi {

    private final ReducerMountingService reducerMountingService;

    @Override
    public ResponseEntity<ResponseDto<List<ReducerMountingDto>>> getAllReducerMounting() {
        return response(reducerMountingService.getAllReducerMounting());
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerMountingDto>> getById(Long id) {
        return response(reducerMountingService.getReducerMountingById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<ReducerMountingDto>> save(ReducerMountingDto reducerMountingDto) {
        return response(reducerMountingService.saveReducerMounting(reducerMountingDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(reducerMountingService.deleteReducerMounting(id));
    }
}
