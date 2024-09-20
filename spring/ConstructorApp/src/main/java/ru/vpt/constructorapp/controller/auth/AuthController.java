package ru.vpt.constructorapp.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.auth.AuthApi;
import ru.vpt.constructorapp.api.auth.dto.LoginDto;
import ru.vpt.constructorapp.api.auth.dto.LoginResponseDto;
import ru.vpt.constructorapp.api.auth.dto.RefreshTokenDto;
import ru.vpt.constructorapp.api.auth.dto.RegistrationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.commercial.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController extends AbstractController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<ResponseDto<LoginResponseDto>> createAuthToken(LoginDto loginDto) {
       return response(authService.createAuthToken(loginDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> createNewUser(RegistrationDto registrationDto) {
        return response(authService.createNewUser(registrationDto));
    }

    @Override
    public ResponseEntity<ResponseDto<LoginResponseDto>> refreshToken(RefreshTokenDto refreshTokenDto) {
        return response(authService.refreshToken(refreshTokenDto));
    }
}
