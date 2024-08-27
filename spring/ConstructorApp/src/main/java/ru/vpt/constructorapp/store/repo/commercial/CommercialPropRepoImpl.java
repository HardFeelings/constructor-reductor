package ru.vpt.constructorapp.store.repo.commercial;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.api.util.QPredicates;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;

import java.util.List;

import static ru.vpt.constructorapp.store.entities.commercial.QCommercialPropEntity.commercialPropEntity;

@RequiredArgsConstructor
public class CommercialPropRepoImpl implements CommercialPropCustomRepo{

    private final EntityManager entityManager;

    @Override
    public List<CommercialPropEntity> findByFilter(CommercialPropDto filter) {
        return new JPAQuery<CommercialPropEntity>(entityManager)
                .select(commercialPropEntity)
                .from(commercialPropEntity)
                .where(QPredicates.builder()
                        .add(filter.getNumber(), commercialPropEntity.number::eq)
                        .add(filter.getManager() != null ? "%" + filter.getManager().getShortName().toLowerCase() + "%" : null,
                                commercialPropEntity.manager.shortName.lower()::like)
                        .add(filter.getPartner() != null ? "%" + filter.getPartner().toLowerCase() + "%" : null, commercialPropEntity.partner.lower()::like)
                        .add(filter.getTimestamp(), commercialPropEntity.timestamp::eq)
                        .buildAnd())
                .fetch();
    }
}
