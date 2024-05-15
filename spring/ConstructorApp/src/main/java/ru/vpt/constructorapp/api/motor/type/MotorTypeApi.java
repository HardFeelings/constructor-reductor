package ru.vpt.constructorapp.api.motor.type;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/motorType")
public interface MotorTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<MotorTypeDto>>> getAllMotorTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<MotorTypeDto>> getById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<MotorTypeDto>> save(@RequestBody MotorTypeDto motorTypeDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
