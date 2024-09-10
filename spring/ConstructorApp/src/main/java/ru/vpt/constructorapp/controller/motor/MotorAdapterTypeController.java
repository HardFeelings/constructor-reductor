package ru.vpt.constructorapp.controller.motor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.motor.adapter.MotorAdapterTypeApi;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterPaginationDto;
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
    public ResponseEntity<ResponseDto<MotorAdapterPaginationDto>> getAllMotorAdapterTypes(Integer offset, Integer limit) {
        return response(motorAdapterTypeService.getAllMotorAdapterTypes(offset, limit));
    }

    @Override
    public ResponseEntity<ResponseDto<MotorAdapterTypeDto>> getById(Long id) {
        return response(motorAdapterTypeService.getMotorAdapterTypeById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<List<MotorAdapterTypeDto>>> getByMotorTypeId(Long id) {
        return response(motorAdapterTypeService.getAllMotorAdapterTypesByMotorTypesId(id));
    }

    @Override
    public ResponseEntity<ResponseDto<MotorAdapterTypeDto>> save(MotorAdapterTypeDto motorAdapterTypeDto) {
        return response(motorAdapterTypeService.saveMotorAdapterType(motorAdapterTypeDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(motorAdapterTypeService.deleteMotorAdapterType(id));
    }
}
