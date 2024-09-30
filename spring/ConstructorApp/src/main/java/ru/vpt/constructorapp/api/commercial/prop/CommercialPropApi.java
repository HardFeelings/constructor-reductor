package ru.vpt.constructorapp.api.commercial.prop;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RequestMapping
public interface CommercialPropApi {
    @GetMapping("/security/commercialProp/{id}")
    ResponseEntity<ResponseDto<CommercialPropDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/commercialProp")
    ResponseEntity<ResponseDto<CommercialPropDto>> save(@RequestBody CommercialPropDto commercialPropDto);

    @PostMapping("/security/commercialProp/getByFilter")
    ResponseEntity<ResponseDto<CommercialPropPaginationDto>> getByFilter(@RequestHeader("Authorization") String token,
                                                                         @RequestBody CommercialPropDto commercialPropDto,
                                                                         @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                         @RequestParam(value = "limit", defaultValue = "15") Integer limit);

    @DeleteMapping("/security/commercialProp/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);

    @GetMapping("/security/commercialProp/report/{id}")
    ResponseEntity<Resource> reportCommercialProp(@PathVariable("id") Long id);

}
