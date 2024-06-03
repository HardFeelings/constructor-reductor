package ru.vpt.constructorapp.api.motor.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface MotorApi {
    @GetMapping("/motor")
    ResponseEntity<ResponseDto<List<MotorDto>>> getAllMotors();

    @GetMapping("/motor/{id}")
    ResponseEntity<ResponseDto<MotorDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/motor")
    ResponseEntity<ResponseDto<MotorDto>> save(@RequestBody MotorDto motorDto);

    @DeleteMapping(value = "/security/motor/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
