package ru.vpt.constructorapp.api.motor.adapter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterPaginationDto;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface MotorAdapterTypeApi {
    @GetMapping("/motorAdapterType")
    ResponseEntity<ResponseDto<MotorAdapterPaginationDto>> getAllMotorAdapterTypes(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                                   @RequestParam(value = "limit", defaultValue = "20") Integer limit);

    @GetMapping("/motorAdapterType/{id}")
    ResponseEntity<ResponseDto<MotorAdapterTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/motorAdapterType/byMotorTypeId/{id}")
    ResponseEntity<ResponseDto<List<MotorAdapterTypeDto>>> getByMotorTypeId(@PathVariable("id") Long id);

    @PostMapping("/security/motorAdapterType")
    ResponseEntity<ResponseDto<MotorAdapterTypeDto>> save(@RequestBody MotorAdapterTypeDto motorAdapterTypeDto);

    @DeleteMapping("/security/motorAdapterType/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
