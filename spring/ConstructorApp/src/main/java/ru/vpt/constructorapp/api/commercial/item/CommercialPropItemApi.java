package ru.vpt.constructorapp.api.commercial.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.commercial.item.dto.CommercialPropItemDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface CommercialPropItemApi {

    @GetMapping("/security/commercialPropItem/byCommercialPropId/{id}")
    ResponseEntity<ResponseDto<List<CommercialPropItemDto>>> getByCommercialPropId(@PathVariable("id") Long id);

    @DeleteMapping("/security/commercialPropItem/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
