package ru.vpt.constructorapp.service.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vpt.constructorapp.store.entities.commercial.Employee;
import ru.vpt.constructorapp.store.repo.commercial.EmployeeRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepo employeeRepo;
    private final RoleService roleService;

    public Optional<Employee> findByLogin(String login) {
        return employeeRepo.findByLogin(login);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepo.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with name '%s' not found ", username)
                ));

        return new User(
                employee.getLogin(),
                employee.getPassword(),
                employee.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList())
        );
    }

    public void createNewEmployee(Employee employee) {
        employee.setRoles(List.of(roleService.findByName("ROLE_USER")));
        employeeRepo.save(employee);
    }
}
