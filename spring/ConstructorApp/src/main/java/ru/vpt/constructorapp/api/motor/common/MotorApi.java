package ru.vpt.constructorapp.api.motor.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.motor.common.dto.MotorPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RequestMapping
public interface MotorApi {
    @GetMapping("/motor")
    ResponseEntity<ResponseDto<MotorPaginationDto>> getAllMotors(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                 @RequestParam(value = "limit", defaultValue = "20") Integer limit);

    @GetMapping("/motor/{id}")
    ResponseEntity<ResponseDto<MotorDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/motor")
    ResponseEntity<ResponseDto<MotorDto>> save(@RequestBody MotorDto motorDto);

    @DeleteMapping(value = "/security/motor/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
