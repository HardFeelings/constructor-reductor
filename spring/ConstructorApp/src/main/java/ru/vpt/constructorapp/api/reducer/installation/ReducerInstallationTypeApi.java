package ru.vpt.constructorapp.api.reducer.installation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/reducerInstallationType")
public interface ReducerInstallationTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerInstallationTypeDto>>> getAllReducerInstallationTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerInstallationTypeDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/byReducerTypeId/{id}")
    ResponseEntity<ResponseDto<List<ReducerInstallationTypeDto>>> getByReducerTypeId(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<ReducerInstallationTypeDto>> save(@RequestBody ReducerInstallationTypeDto reducerInstallationTypeDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
