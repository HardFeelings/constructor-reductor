package ru.vpt.constructorapp.api.motor.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/motor")
public interface MotorApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<MotorDto>>> getAllMotors();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<MotorDto>> getById(@PathVariable("id") Long id);
}
