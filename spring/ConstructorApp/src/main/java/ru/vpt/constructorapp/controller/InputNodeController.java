package ru.vpt.constructorapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.input.node.InputNodeApi;
import ru.vpt.constructorapp.api.input.node.dto.InputNodeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InputNodeController extends AbstractResponseController implements InputNodeApi {
    @Override
    public ResponseEntity<ResponseDto<List<InputNodeDto>>> getAllInputNode() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto<InputNodeDto>> getById(Long id) {
        return null;
    }
}
