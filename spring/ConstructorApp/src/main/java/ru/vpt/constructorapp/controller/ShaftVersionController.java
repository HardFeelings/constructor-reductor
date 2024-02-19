package ru.vpt.constructorapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.shaft.version.ShaftVersionApi;
import ru.vpt.constructorapp.api.shaft.version.dto.ShaftVersionDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShaftVersionController extends AbstractResponseController implements ShaftVersionApi {
    @Override
    public ResponseEntity<ResponseDto<List<ShaftVersionDto>>> getAllShaftVersion() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto<ShaftVersionDto>> getById(Long id) {
        return null;
    }
}
