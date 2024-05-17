package ru.vpt.constructorapp.api.motor.type;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface MotorTypeApi {
    @GetMapping("/motorType")
    ResponseEntity<ResponseDto<List<MotorTypeDto>>> getAllMotorTypes();

    @GetMapping("/motorType/{id}")
    ResponseEntity<ResponseDto<MotorTypeDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/motorType")
    ResponseEntity<ResponseDto<MotorTypeDto>> save(@RequestBody MotorTypeDto motorTypeDto);

    @DeleteMapping("/security/motorType/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
