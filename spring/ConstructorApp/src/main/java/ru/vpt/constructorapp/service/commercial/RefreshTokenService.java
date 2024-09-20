package ru.vpt.constructorapp.service.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vpt.constructorapp.api.exception.TokenRefreshException;
import ru.vpt.constructorapp.store.entities.commercial.Employee;
import ru.vpt.constructorapp.store.entities.commercial.RefreshToken;
import ru.vpt.constructorapp.store.repo.commercial.EmployeeRepo;
import ru.vpt.constructorapp.store.repo.commercial.RefreshTokenRepo;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Value("${jwt.refreshtime}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepo refreshTokenRepo;
    private final EmployeeRepo employeeRepo;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepo.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setEmployee(employeeRepo.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepo.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepo.deleteByEmployee(employeeRepo.findById(userId).get());
    }

    public Boolean existsByEmployee(Employee employee) {
        return refreshTokenRepo.existsByEmployee(employee);
    }
}
