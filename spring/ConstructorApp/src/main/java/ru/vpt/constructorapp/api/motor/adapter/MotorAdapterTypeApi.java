package ru.vpt.constructorapp.api.motor.adapter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/motorAdapterType")
public interface MotorAdapterTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<MotorAdapterTypeDto>>> getAllMotorAdapterTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<MotorAdapterTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/byMotorTypeId/{id}")
    ResponseEntity<ResponseDto<List<MotorAdapterTypeDto>>> getByMotorTypeId(@PathVariable("id") Long id);
}
