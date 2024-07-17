package ru.vpt.constructorapp.controller.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.commercial.item.CommercialPropItemApi;
import ru.vpt.constructorapp.api.commercial.item.dto.CommercialPropItemDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.commercial.CommercialPropItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommercialPropItemController extends AbstractController implements CommercialPropItemApi {

    private final CommercialPropItemService service;

    @Override
    public ResponseEntity<ResponseDto<List<CommercialPropItemDto>>> getByCommercialPropId(Long id) {
        return response(service.getAllByCommercialPropId(id));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(service.delete(id));
    }
}
