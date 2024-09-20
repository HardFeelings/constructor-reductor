package ru.vpt.constructorapp.api.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.auth.dto.LoginDto;
import ru.vpt.constructorapp.api.auth.dto.LoginResponseDto;
import ru.vpt.constructorapp.api.auth.dto.RefreshTokenDto;
import ru.vpt.constructorapp.api.auth.dto.RegistrationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RequestMapping
public interface AuthApi {
    @PostMapping("/auth")
    ResponseEntity<ResponseDto<LoginResponseDto>> createAuthToken(@RequestBody LoginDto loginDto);

    @PostMapping("/security/admin/registration")
    ResponseEntity<ResponseDto<Boolean>> createNewUser(@RequestBody RegistrationDto registrationDto);

    @PostMapping("/refresh")
    ResponseEntity<ResponseDto<LoginResponseDto>> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto);
}
