package ru.vpt.constructorapp.service.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vpt.constructorapp.api.auth.dto.RegistrationDto;
import ru.vpt.constructorapp.api.commercial.employee.dto.EmployeeDto;
import ru.vpt.constructorapp.api.commercial.employee.dto.EmployeePaginationDto;
import ru.vpt.constructorapp.api.commercial.employee.mapper.EmployeeMapper;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerPaginationDto;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.store.entities.commercial.Employee;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;
import ru.vpt.constructorapp.store.repo.commercial.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {

    private EmployeeRepo employeeRepo;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private final EmployeeMapper employeeMapper;
    private final RefreshTokenService refreshTokenService;

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

    public EmployeePaginationDto getAll(Integer offset, Integer limit) {
        Page<Employee> page = employeeRepo.findAll(PageRequest.of(offset, limit, Sort.by("idEmployee")));
        List<EmployeeDto> dtos = new ArrayList<>();
        page.getContent().forEach(item -> dtos.add(employeeMapper.toDTO(item)));
        EmployeePaginationDto paginationDto = new EmployeePaginationDto();
        paginationDto.setContent(dtos);
        paginationDto.setTotalCount(page.getTotalElements());
        paginationDto.setTotalPages(page.getTotalPages());
        paginationDto.setCurrentPage(page.getNumber());
        return paginationDto;
    }


    public Employee findById(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found "));
    }


    public EmployeeDto save(EmployeeDto employeeDto) {
        if (Objects.isNull(employeeDto))
            throw new BadRequestException("Невозможно сохранить пользователя: dto равен null", 400);
        if (Objects.isNull(employeeDto.getPassword()) || employeeDto.getPassword().isEmpty())
            throw new BadRequestException("Невозможно сохранить пользователя: пароль равен null", 400);
        if (Objects.isNull(employeeDto.getLogin()) || employeeDto.getLogin().isEmpty())
            throw new BadRequestException("Невозможно сохранить пользователя: имя пользователя равно null", 400);
        if (employeeDto.getIdEmployee() == null && employeeRepo.existsByLogin(employeeDto.getLogin()))
            throw new BadRequestException("Невозможно сохранить пользователя: данный пользователь уже существует", 400);

        Employee employee = employeeMapper.toEntity(employeeDto);
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        if (Objects.isNull(employeeDto.getAdmin()) || !employeeDto.getAdmin())
            employee.setRoles(List.of(roleService.findByName("ROLE_USER")));
        else
            employee.setRoles(List.of(roleService.findByName("ROLE_ADMIN"), roleService.findByName("ROLE_USER")));
        return employeeMapper.toDTO(employeeRepo.save(employee));
    }

    public Boolean delete(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить пользователя: id равен null", 400);
        }
        if (!employeeRepo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить пользователя: не найден объект с id: " + id, 404);
        }
        refreshTokenService.deleteByUserId(id);
        employeeRepo.deleteById(id);
        return true;
    }
}

