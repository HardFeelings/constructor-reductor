package ru.vpt.constructorapp.controller.util;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.vpt.constructorapp.api.util.ResponseDto;

public class AbstractController {

    protected <Dto> ResponseEntity<ResponseDto<Dto>> response(Dto dto) {
        ResponseDto<Dto> response = new ResponseDto<>();
        response.setData(dto);
        response.setError(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
