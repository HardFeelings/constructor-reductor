package ru.vpt.constructorapp.api.email;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.email.dto.EMailDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RequestMapping
public interface EMailApi {

    @PostMapping("/send-mail")
    ResponseEntity<ResponseDto<Boolean>> sendMail(@RequestBody EMailDto emailDto);
}
