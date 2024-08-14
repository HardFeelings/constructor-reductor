package ru.vpt.constructorapp.controller.email;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.email.EMailApi;
import ru.vpt.constructorapp.api.email.dto.EMailDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.email.EMailService;


@RestController
@RequiredArgsConstructor
public class EMailController extends AbstractController implements EMailApi {

    private final EMailService emailService;

    @Override
    public ResponseEntity<ResponseDto<Boolean>> sendMail(EMailDto emailDto) {
        return response(emailService.sendMail(emailDto));
    }
}
