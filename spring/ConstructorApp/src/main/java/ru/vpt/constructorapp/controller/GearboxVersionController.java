package ru.vpt.constructorapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.gearbox.version.GearboxVersionApi;
import ru.vpt.constructorapp.api.gearbox.version.dto.GearboxVersionDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GearboxVersionController extends AbstractResponseController implements GearboxVersionApi {
    @Override
    public ResponseEntity<ResponseDto<List<GearboxVersionDto>>> getAllGearboxVersion() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto<GearboxVersionDto>> getById(Long id) {
        return null;
    }
}
