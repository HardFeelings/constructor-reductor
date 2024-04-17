package ru.vpt.constructorapp.controller.motor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.motor.adapter.MotorAdapterTypeApi;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.motor.MotorAdapterTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MotorAdapterTypeController extends AbstractController implements MotorAdapterTypeApi {

    private final MotorAdapterTypeService motorAdapterTypeService;

    @Override
    public ResponseEntity<ResponseDto<List<MotorAdapterTypeDto>>> getAllMotorAdapterTypes() {
        return response(motorAdapterTypeService.getAllMotorAdapterTypes());
    }

    @Override
    public ResponseEntity<ResponseDto<MotorAdapterTypeDto>> getById(Long id) {
        return response(motorAdapterTypeService.getMotorAdapterTypeById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<List<MotorAdapterTypeDto>>> getByMotorTypeId(Long id) {
        return response(motorAdapterTypeService.getAllMotorAdapterTypesByMotorTypesId(id));
    }
}
