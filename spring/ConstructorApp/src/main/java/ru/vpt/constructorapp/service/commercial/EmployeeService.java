package ru.vpt.constructorapp.service.commercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vpt.constructorapp.api.auth.dto.RegistrationDto;
import ru.vpt.constructorapp.store.entities.commercial.Employee;
import ru.vpt.constructorapp.store.repo.commercial.EmployeeRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements UserDetailsService {

    private EmployeeRepo employeeRepo;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setEmployeeRepo(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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

    public Boolean createNewEmployee(RegistrationDto registrationDto) {
        Employee employee = new Employee();
        employee.setLogin(registrationDto.getUsername());
        employee.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        if(registrationDto.getIsAdmin())
            employee.setRoles(List.of(roleService.findByName("ROLE_ADMIN"), roleService.findByName("ROLE_USER")));
        else
            employee.setRoles(List.of(roleService.findByName("ROLE_USER")));
        employeeRepo.save(employee);
        return true;
    }

    public Employee findById(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found "));
    }
}
