package ru.vpt.constructorapp.api.motor.common;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.motor.common.dto.MotorPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RequestMapping
public interface MotorApi {
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/motor")
    ResponseEntity<ResponseDto<MotorPaginationDto>> getAllMotors(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                 @RequestParam(value = "limit", defaultValue = "15") Integer limit);
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/motor/{id}")
    ResponseEntity<ResponseDto<MotorDto>> getById(@PathVariable("id") Long id);
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/motor")
    ResponseEntity<ResponseDto<MotorDto>> save(@RequestBody MotorDto motorDto);
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "/security/motor/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
