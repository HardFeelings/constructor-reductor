package ru.vpt.constructorapp.api.motor.type;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface MotorTypeApi {
    @GetMapping("/motorType")
    ResponseEntity<ResponseDto<List<MotorTypeDto>>> getAllMotorTypes();

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/motorType/{id}")
    ResponseEntity<ResponseDto<MotorTypeDto>> getById(@PathVariable("id") Long id);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/motorType")
    ResponseEntity<ResponseDto<MotorTypeDto>> save(@RequestBody MotorTypeDto motorTypeDto);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/security/motorType/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
