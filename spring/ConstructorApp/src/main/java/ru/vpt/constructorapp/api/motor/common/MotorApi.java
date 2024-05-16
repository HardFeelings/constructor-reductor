package ru.vpt.constructorapp.api.motor.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/motor")
public interface MotorApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<MotorDto>>> getAllMotors();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<MotorDto>> getById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<MotorDto>> save(@RequestBody MotorDto motorDto);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable Long id);
}
