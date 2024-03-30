package ru.vpt.constructorapp.api.util;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDto <T>{
    private T data;

    private Exception error;
}