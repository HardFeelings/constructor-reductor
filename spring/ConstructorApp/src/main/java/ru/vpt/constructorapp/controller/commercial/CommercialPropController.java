package ru.vpt.constructorapp.controller.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.commercial.prop.CommercialPropApi;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.commercial.CommercialPropService;

import java.io.BufferedInputStream;

@RestController
@RequiredArgsConstructor
public class CommercialPropController extends AbstractController implements CommercialPropApi {

    private final CommercialPropService service;

//    @Override
//    public ResponseEntity<ResponseDto<List<CommercialPropDto>>> getAll() {
//        return response(service.getAll());
//    }

    @Override
    public ResponseEntity<ResponseDto<CommercialPropDto>> getById(Long id) {
        return response(service.getById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<CommercialPropDto>> save(CommercialPropDto commercialPropDto) {
        return response(service.save(commercialPropDto));
    }

    @Override
    public ResponseEntity<ResponseDto<CommercialPropPaginationDto>> getByFilter(CommercialPropDto commercialPropDto, Integer offset, Integer limit) {
        return response(service.getByFilter(commercialPropDto, offset, limit));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(service.delete(id));
    }

    @Override
    public ResponseEntity<Resource> reportCommercialProp(Long id) {
        BufferedInputStream inputStream = null;
        inputStream = service.report(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);
    }
}
