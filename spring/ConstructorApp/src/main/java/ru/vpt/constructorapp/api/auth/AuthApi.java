package ru.vpt.constructorapp.api.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.auth.dto.LoginDto;

@RequestMapping
public interface AuthApi {
    @PostMapping("/auth")
    ResponseEntity<?> createAuthToken(@RequestBody LoginDto loginDto);

}
