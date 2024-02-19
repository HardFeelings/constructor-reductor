package ru.vpt.constructorapp.api.gearbox.version;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.gearbox.version.dto.GearboxVersionDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/gearboxVersion")
public interface GearboxVersionApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<GearboxVersionDto>>> getAllGearboxVersion();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<GearboxVersionDto>> getById(@PathVariable Long id);
}
