package ru.vpt.constructorapp.service.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.auth.dto.LoginDto;
import ru.vpt.constructorapp.api.auth.dto.LoginResponseDto;
import ru.vpt.constructorapp.api.auth.dto.RegistrationDto;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.util.JwtUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final EmployeeService employeeService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDto createAuthToken(LoginDto loginDto){
        if(Objects.isNull(loginDto))
            throw new BadRequestException("Невозможно получить токен: dto равен null", 400);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadRequestException("Неправильное имя или пароль", 400);
        }
        UserDetails userDetails = employeeService.loadUserByUsername(loginDto.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return new LoginResponseDto(token);
    }

    public Boolean createNewUser(RegistrationDto registrationDto) {
        if(Objects.isNull(registrationDto))
            throw new BadRequestException("Невозможно зарегистрировать пользователя: dto равен null", 400);
        if(Objects.isNull(registrationDto.getPassword()) || registrationDto.getPassword().isEmpty())
            throw new BadRequestException("Невозможно зарегистрировать пользователя: пароль равен null", 400);
        if(Objects.isNull(registrationDto.getConfirmPassword()) || !registrationDto.getConfirmPassword().equals(registrationDto.getPassword()))
            throw new BadRequestException("Невозможно зарегистрировать пользователя: пароли должны совпадать", 400);
        if (Objects.isNull(registrationDto.getUsername()) || registrationDto.getUsername().isEmpty())
            throw new BadRequestException("Невозможно зарегистрировать пользователя: имя пользователя равно null", 400);
        if(employeeService.findByLogin(registrationDto.getUsername()).isPresent())
            throw new BadRequestException("Невозможно зарегистрировать пользователя: данный пользователь уже существует", 400);

        return employeeService.createNewEmployee(registrationDto);

    }
}
