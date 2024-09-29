package ru.vpt.constructorapp.service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.email.dto.EMailDto;
import ru.vpt.constructorapp.api.exception.BadRequestException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EMailService {

    private final JavaMailSender emailSender;

    public Boolean sendMail(EMailDto dto) {
        if (!Objects.isNull(dto.getProductName()) && !Objects.isNull(dto.getName()) && !Objects.isNull(dto.getEmail()) && !Objects.isNull(dto.getPhoneNumber())) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("site@extindtrade.com");
            simpleMailMessage.setTo("office@extindtrade.com");
            simpleMailMessage.setSubject("Заявка из конфигуратора");
            simpleMailMessage.setText(generateMessage(dto));
            emailSender.send(simpleMailMessage);
            return true;
        } else
         throw new BadRequestException("Invalid data for send email", 400);
    }

    private String generateMessage(EMailDto dto) {
        StringBuffer sb = new StringBuffer();
        sb.append("Имя пользователя: ").append(dto.getName()).append("\n")
                .append("Почта: ").append(dto.getEmail()).append("\n")
                .append("Телефон для связи: ").append(dto.getPhoneNumber()).append("\n")
                .append("Оборудование: ").append(dto.getProductName()).append("\n")
                .append("Описание: ").append(dto.getInfo());
        return sb.toString();
    }
}
