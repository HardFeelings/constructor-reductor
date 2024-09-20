package ru.vpt.constructorapp.service.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.auth.dto.LoginDto;
import ru.vpt.constructorapp.api.auth.dto.LoginResponseDto;
import ru.vpt.constructorapp.api.auth.dto.RefreshTokenDto;
import ru.vpt.constructorapp.api.auth.dto.RegistrationDto;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.TokenRefreshException;
import ru.vpt.constructorapp.store.entities.commercial.Employee;
import ru.vpt.constructorapp.store.entities.commercial.RefreshToken;
import ru.vpt.constructorapp.util.JwtUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final EmployeeService employeeService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public LoginResponseDto createAuthToken(LoginDto loginDto) {
        if (Objects.isNull(loginDto))
            throw new BadRequestException("Невозможно получить токен: dto равен null", 400);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadRequestException("Неправильное имя или пароль", 400);
        }
        UserDetails userDetails = employeeService.loadUserByUsername(loginDto.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        Employee employee = employeeService.findByLogin(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with name '%s' not found ", userDetails.getUsername())
        ));
        RefreshToken refreshToken;
        if(refreshTokenService.existsByEmployee(employee)) {
            refreshTokenService.deleteByUserId(employee.getIdEmployee());
        }
        refreshToken = refreshTokenService.createRefreshToken(employee.getIdEmployee());
        return new LoginResponseDto(token, refreshToken.getToken());
    }

    public Boolean createNewUser(RegistrationDto registrationDto) {
        if (Objects.isNull(registrationDto))
            throw new BadRequestException("Невозможно зарегистрировать пользователя: dto равен null", 400);
        if (Objects.isNull(registrationDto.getPassword()) || registrationDto.getPassword().isEmpty())
            throw new BadRequestException("Невозможно зарегистрировать пользователя: пароль равен null", 400);
        if (Objects.isNull(registrationDto.getConfirmPassword()) || !registrationDto.getConfirmPassword().equals(registrationDto.getPassword()))
            throw new BadRequestException("Невозможно зарегистрировать пользователя: пароли должны совпадать", 400);
        if (Objects.isNull(registrationDto.getUsername()) || registrationDto.getUsername().isEmpty())
            throw new BadRequestException("Невозможно зарегистрировать пользователя: имя пользователя равно null", 400);
        if (employeeService.findByLogin(registrationDto.getUsername()).isPresent())
            throw new BadRequestException("Невозможно зарегистрировать пользователя: данный пользователь уже существует", 400);

        return employeeService.createNewEmployee(registrationDto);

    }

    public LoginResponseDto refreshToken(RefreshTokenDto refreshTokenDto) {
        if(Objects.isNull(refreshTokenDto))
            throw new BadRequestException("Refresh token is null", 400);
        RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenDto.getToken()).orElseThrow(() ->
                new TokenRefreshException(refreshTokenDto.getToken(), "invalid refresh token")
        );
        refreshToken = refreshTokenService.verifyExpiration(refreshToken);
        UserDetails userDetails = employeeService.loadUserByUsername(refreshToken.getEmployee().getLogin());
        String token = jwtUtils.generateToken(userDetails);
        return new LoginResponseDto(token, refreshToken.getToken());
    }
}
