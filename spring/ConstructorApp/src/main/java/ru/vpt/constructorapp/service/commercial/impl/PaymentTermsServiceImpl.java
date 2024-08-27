package ru.vpt.constructorapp.service.commercial.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.commercial.terms.dto.PaymentTermsDto;
import ru.vpt.constructorapp.api.commercial.terms.mapper.PaymentTermsMapper;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.service.commercial.PaymentTermsService;
import ru.vpt.constructorapp.store.entities.commercial.PaymentTermsEntity;
import ru.vpt.constructorapp.store.repo.commercial.PaymentTermsRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentTermsServiceImpl implements PaymentTermsService {

    private final PaymentTermsMapper mapper;
    private final PaymentTermsRepo repo;


    @Override
    public List<PaymentTermsDto> getAll() {
        return repo.findAll().stream().
                map(mapper::toDTO).sorted(Comparator.comparingLong(PaymentTermsDto::getIdPaymentTerms))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentTermsDto getById(Long id) {
        PaymentTermsEntity entity = repo.findById(id).orElseThrow(() -> new NotFoundException("payment terms with id = " + id + " not found", 404));
        return mapper.toDTO(entity);
    }

    @Override
    public PaymentTermsEntity findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("payment terms with id = " + id + " not found", 404));
    }

    @Override
    public PaymentTermsDto save(PaymentTermsDto paymentTermsDto) {
        if (Objects.isNull(paymentTermsDto)) {
            throw new BadRequestException("Невозможно сохранить объект: dto равен null", 400);
        }
        PaymentTermsEntity entity = mapper.toEntity(paymentTermsDto);
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
}
