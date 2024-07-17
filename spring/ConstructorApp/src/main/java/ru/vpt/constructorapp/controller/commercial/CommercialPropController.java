package ru.vpt.constructorapp.controller.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.commercial.prop.CommercialPropApi;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.commercial.CommercialPropService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommercialPropController extends AbstractController implements CommercialPropApi {

    private final CommercialPropService service;

    @Override
    public ResponseEntity<ResponseDto<List<CommercialPropDto>>> getAll() {
        return response(service.getAll());
    }

    @Override
    public ResponseEntity<ResponseDto<CommercialPropDto>> getById(Long id) {
        return response(service.getById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<CommercialPropDto>> save(CommercialPropDto commercialPropDto) {
        return response(service.save(commercialPropDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(service.delete(id));
    }
}
