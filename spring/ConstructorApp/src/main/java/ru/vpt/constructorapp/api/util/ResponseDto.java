package ru.vpt.constructorapp.api.util;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDto <T>{
    private T data;

    private List<Exception> error;
}