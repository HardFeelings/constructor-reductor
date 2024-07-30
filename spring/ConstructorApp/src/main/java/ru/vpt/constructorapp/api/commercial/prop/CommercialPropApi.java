package ru.vpt.constructorapp.api.commercial.prop;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface CommercialPropApi {
    @GetMapping("/security/commercialProp")
    ResponseEntity<ResponseDto<List<CommercialPropDto>>> getAll();

    @GetMapping("/security/commercialProp/{id}")
    ResponseEntity<ResponseDto<CommercialPropDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/commercialProp")
    ResponseEntity<ResponseDto<CommercialPropDto>> save(@RequestBody CommercialPropDto commercialPropDto);

    @PostMapping("/security/commercialProp/getByFilter")
    ResponseEntity<ResponseDto<List<CommercialPropDto>>> getByFilter(@RequestBody CommercialPropDto commercialPropDto);

    @DeleteMapping("/security/commercialProp/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);

    @GetMapping("/security/commercialProp/report/{id}")
    ResponseEntity<Resource> reportCommercialProp(@PathVariable("id") Long id);

}
