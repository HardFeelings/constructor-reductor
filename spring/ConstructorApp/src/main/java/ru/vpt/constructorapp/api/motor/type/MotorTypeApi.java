package ru.vpt.constructorapp.api.motor.type;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/motorType")
public interface MotorTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<MotorTypeDto>>> getAllMotorTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<MotorTypeDto>> getById(@PathVariable("id") Long id);
}
