package ru.vpt.constructorapp.controller.motor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.motor.common.MotorApi;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.motor.MotorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MotorController extends AbstractController implements MotorApi {

    private final MotorService motorService;

    @Override
    public ResponseEntity<ResponseDto<List<MotorDto>>> getAllMotors() {
        return response(motorService.getAllMotors());
    }

    @Override
    public ResponseEntity<ResponseDto<MotorDto>> getById(Long id) {
        return response(motorService.getMotorById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<MotorDto>> save(MotorDto motorDto) {
        return response(motorService.saveMotor(motorDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(motorService.deleteMotor(id));
    }

}
