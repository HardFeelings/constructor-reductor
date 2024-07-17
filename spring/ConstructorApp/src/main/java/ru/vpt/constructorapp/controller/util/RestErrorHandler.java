package ru.vpt.constructorapp.controller.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RestControllerAdvice
public class RestErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDto<?>> notFoundException(NotFoundException exception) {
        return new ResponseEntity<>(ResponseDto.builder()
                .data(null)
                .errorMsg(exception.getErrorMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseDto<?>> badRequestException(BadRequestException exception) {
        return new ResponseEntity<>(ResponseDto.builder()
                .data(null)
                .errorMsg(exception.getErrorMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}