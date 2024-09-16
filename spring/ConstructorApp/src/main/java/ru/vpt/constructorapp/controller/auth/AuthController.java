package ru.vpt.constructorapp.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.auth.AuthApi;
import ru.vpt.constructorapp.api.auth.dto.LoginDto;
import ru.vpt.constructorapp.api.auth.dto.LoginResponseDto;
import ru.vpt.constructorapp.api.exception.AppError;
import ru.vpt.constructorapp.service.commercial.EmployeeService;
import ru.vpt.constructorapp.util.JwtUtils;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final EmployeeService employeeService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> createAuthToken(LoginDto loginDto) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "invalid username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = employeeService.loadUserByUsername(loginDto.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
