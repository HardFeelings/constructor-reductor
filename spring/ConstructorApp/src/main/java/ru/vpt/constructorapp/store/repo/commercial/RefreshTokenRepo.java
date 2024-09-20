package ru.vpt.constructorapp.store.repo.commercial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.commercial.Employee;
import ru.vpt.constructorapp.store.entities.commercial.RefreshToken;

import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    @Modifying
    int deleteByEmployee(Employee employee);

    Boolean existsByEmployee(Employee employee);
}