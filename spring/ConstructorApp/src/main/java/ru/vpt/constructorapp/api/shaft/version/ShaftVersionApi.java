package ru.vpt.constructorapp.api.shaft.version;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.shaft.version.dto.ShaftVersionDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/shaftVersion")
public interface ShaftVersionApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ShaftVersionDto>>> getAllShaftVersion();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ShaftVersionDto>> getById(@PathVariable Long id);
}
