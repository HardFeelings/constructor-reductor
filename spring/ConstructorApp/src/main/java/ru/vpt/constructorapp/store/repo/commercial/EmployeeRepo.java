package ru.vpt.constructorapp.store.repo.commercial;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.commercial.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    Optional<Employee> findByLogin(String login);
}
