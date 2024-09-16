package ru.vpt.constructorapp.controller.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.commercial.manager.ManagerApi;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.commercial.ManagerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerController extends AbstractController implements ManagerApi {

    private final ManagerService service;

    @Override
    public ResponseEntity<ResponseDto<ManagerPaginationDto>> getAll(Integer offset, Integer limit) {
        return response(service.getAll(offset, limit));
    }

    @Override
    public ResponseEntity<ResponseDto<ManagerDto>> getById(Long id) {
        return response(service.getById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<ManagerDto>> save(ManagerDto managerDto) {
        return response(service.save(managerDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(service.delete(id));
    }

    @Override
    public ResponseEntity<ResponseDto<List<ManagerDto>>> getAllWithoutPagination() {
        return response(service.getAllWithoutPagination());
    }
}
