package ru.vpt.constructorapp.api.reducer.installation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/reducerInstallationType")
public interface ReducerInstallationTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ReducerInstallationTypeDto>>> getAllReducerInstallationTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ReducerInstallationTypeDto>> getById(@PathVariable("id") Long id);
}
