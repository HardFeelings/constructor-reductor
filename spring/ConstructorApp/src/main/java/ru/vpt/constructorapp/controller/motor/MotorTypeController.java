package ru.vpt.constructorapp.controller.motor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.motor.type.MotorTypeApi;
import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.motor.MotorTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MotorTypeController extends AbstractController implements MotorTypeApi {

    private final MotorTypeService motorTypeService;

    @Override
    public ResponseEntity<ResponseDto<List<MotorTypeDto>>> getAllMotorTypes() {
        return response(motorTypeService.getAllMotorTypes());
    }

    @Override
    public ResponseEntity<ResponseDto<MotorTypeDto>> getById(Long id) {
        return response(motorTypeService.getMotorTypeById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<MotorTypeDto>> save(MotorTypeDto motorTypeDto) {
        return response(motorTypeService.saveMotorType(motorTypeDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(motorTypeService.deleteMotorType(id));
    }
}
