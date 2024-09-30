package ru.vpt.constructorapp.service.commercial.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerPaginationDto;
import ru.vpt.constructorapp.api.commercial.manager.mapper.ManagerMapper;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.service.commercial.EmployeeService;
import ru.vpt.constructorapp.service.commercial.ManagerService;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;
import ru.vpt.constructorapp.store.repo.commercial.ManagerRepo;
import ru.vpt.constructorapp.util.JwtUtils;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepo repo;
    private final ManagerMapper mapper;
    private final EmployeeService employeeService;
    private final JwtUtils jwtUtils;

    @Override
    public ManagerPaginationDto getAll(int offset, int limit) {
        Page<ManagerEntity> page = repo.findAll(PageRequest.of(offset, limit, Sort.by("idManager")));
        List<ManagerDto> dtos = new ArrayList<>();
        page.getContent().forEach(item -> dtos.add(mapper.toDTO(item)));
        ManagerPaginationDto paginationDto = new ManagerPaginationDto();
        paginationDto.setContent(dtos);
        paginationDto.setTotalCount(page.getTotalElements());
        paginationDto.setTotalPages(page.getTotalPages());
        paginationDto.setCurrentPage(page.getNumber());
        return paginationDto;
    }

    @Override
    public ManagerDto getById(Long id) {
        ManagerEntity entity = repo.findById(id).orElseThrow(() -> new NotFoundException("manager with id = " + id + " not found", 404));
        return mapper.toDTO(entity);
    }

    @Override
    public ManagerEntity findById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно получить объект: id равен null", 400);
        }
        return repo.findById(id).orElse(null);
    }

    @Override
    public ManagerDto save(ManagerDto managerDto) {
        if (Objects.isNull(managerDto)) {
            throw new BadRequestException("Невозможно сохранить объект: dto равен null", 400);
        }
        ManagerEntity entity = mapper.toEntity(managerDto);
        entity.setEmployee(employeeService.findById(managerDto.getIdUser()));
        return mapper.toDTO(repo.save(entity));
    }

    @Override
    public Boolean delete(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить объект: id равен null", 400);
        }
        if (!repo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить объект: не найден объект с id: " + id, 404);
        }
        repo.deleteById(id);
        return true;
    }

    @Override
    public List<ManagerDto> getAllWithoutPagination(String token) {
        List<String> roles = jwtUtils.getRoles(token.substring(7));
        List<ManagerEntity> entites;
        for (String role : roles) {
            if(role.contains("ADMIN")) {
                entites = repo.findAll();
                List<ManagerDto> dtos = new ArrayList<>();
                entites.forEach(item -> dtos.add(mapper.toDTO(item)));
                return dtos;
            }
        }
        String user = jwtUtils.getUsername(token.substring(7));
        entites = repo.findAllByEmployee_Login(user);
        List<ManagerDto> dtos = new ArrayList<>();
        entites.forEach(item -> dtos.add(mapper.toDTO(item)));
        return dtos;

    }

    @Override
    public ManagerEntity getByUsername(String username) {
        return repo.findByEmployee_Login(username).orElseThrow(() -> new NotFoundException("manager with name = " + username + " not found", 404));
    }
}
