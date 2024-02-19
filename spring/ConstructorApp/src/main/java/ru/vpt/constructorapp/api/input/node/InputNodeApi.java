package ru.vpt.constructorapp.api.input.node;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.input.node.dto.InputNodeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/inputNode")
public interface InputNodeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<InputNodeDto>>> getAllInputNode();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<InputNodeDto>> getById(@PathVariable Long id);
}
