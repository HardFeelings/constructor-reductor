package ru.vpt.constructorapp.service.commercial.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.commercial.payment.dto.CommercialPropTermsDto;
import ru.vpt.constructorapp.api.commercial.payment.mapper.CommercialPropTermsMapper;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.service.commercial.CommercialPropTermsService;
import ru.vpt.constructorapp.service.commercial.PaymentTermsService;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropTermsEntity;
import ru.vpt.constructorapp.store.entities.commercial.PaymentTermsEntity;
import ru.vpt.constructorapp.store.repo.commercial.CommercialPropRepo;
import ru.vpt.constructorapp.store.repo.commercial.CommercialPropTermsRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommercialPropTermsServiceImpl implements CommercialPropTermsService {

    private final CommercialPropTermsRepo repo;
    private final CommercialPropTermsMapper mapper;

    private final PaymentTermsService paymentTermsService;
    private final CommercialPropRepo commercialPropRepo;

    @Override
    public List<CommercialPropTermsDto> getAllByCommercialPropId(Long id) {
        if(Objects.isNull(id)){
            throw new BadRequestException("invalid request: id can not be null", 400);
        }
        return repo.findAllByCommercialProp_IdCommercialProp(id).stream().
                map(mapper::toDTO).sorted(Comparator.comparingLong(CommercialPropTermsDto::getIdCommercialPropTerms))
                .collect(Collectors.toList());
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
    public CommercialPropTermsDto save(CommercialPropTermsDto commercialPropTermsDto, Long id) {
        if (Objects.isNull(commercialPropTermsDto)) {
            throw new BadRequestException("Невозможно сохранить элемент: dto равен null", 400);
        }
        CommercialPropEntity commercialProp = commercialPropRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("commercialProp with id = " + id + " not found", 404));

        PaymentTermsEntity paymentTerms = paymentTermsService.findById(commercialPropTermsDto.getPaymentTerms().getIdPaymentTerms());
        CommercialPropTermsEntity entity = mapper.toEntity(commercialPropTermsDto);
        entity.setCommercialProp(commercialProp);
        entity.setPaymentTerms(paymentTerms);
        return mapper.toDTO(repo.save(entity));
    }
}
