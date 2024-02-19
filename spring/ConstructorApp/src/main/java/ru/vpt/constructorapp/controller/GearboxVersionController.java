package ru.vpt.constructorapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.gearbox.version.GearboxVersionApi;
import ru.vpt.constructorapp.api.gearbox.version.dto.GearboxVersionDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.service.GearboxVersionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GearboxVersionController extends AbstractResponseController implements GearboxVersionApi {
    private final GearboxVersionService gearboxVersionService;
    @Override
    public ResponseEntity<ResponseDto<List<GearboxVersionDto>>> getAllGearboxVersion() {
        return response(gearboxVersionService.getAllGearboxVersion());
    }

    @Override
    public ResponseEntity<ResponseDto<GearboxVersionDto>> getById(Long id) {
        return response(gearboxVersionService.getById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<List<GearboxVersionDto>>> getByReducerTypeId(Long id) {
        return response(gearboxVersionService.getByReducerTypeId(id));
    }

}
