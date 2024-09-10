package ru.vpt.constructorapp.store.repo.commercial;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.api.util.QPredicates;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;

import java.util.List;

import static java.util.Optional.ofNullable;
import static ru.vpt.constructorapp.store.entities.commercial.QCommercialPropEntity.commercialPropEntity;

@Repository
public class CommercialPropRepoImpl extends QuerydslRepositorySupport implements CommercialPropCustomRepo{

    private final EntityManager entityManager;

    public CommercialPropRepoImpl(EntityManager em) {
        super(CommercialPropEntity.class);
        this.entityManager = em;
    }

    @Override
    public Page<CommercialPropEntity> findByFilter(CommercialPropDto filter, Pageable pageable) {
        Long totalCount = getJPAQuery(filter, commercialPropEntity.count()).fetchOne();
        JPAQuery<CommercialPropEntity> query = getJPAQuery(filter, commercialPropEntity);
        ofNullable(getQuerydsl()).ifPresent(querydsl -> querydsl.applyPagination(pageable, query));
        List<CommercialPropEntity> pagedData = query.fetch();
        return PageableExecutionUtils.getPage(pagedData, pageable, () -> totalCount);
    }


    private <T> JPAQuery<T> getJPAQuery(CommercialPropDto filter, Expression<T> expression) {
        return new JPAQuery<T>(entityManager)
                .select(expression)
                .from(commercialPropEntity)
                .where(QPredicates.builder()
                        .add(filter.getNumber(), commercialPropEntity.number::eq)
                        .add(filter.getManager() != null ? "%" + filter.getManager().getShortName().toLowerCase() + "%" : null,
                                commercialPropEntity.manager.shortName.lower()::like)
                        .add(filter.getPartner() != null ? "%" + filter.getPartner().toLowerCase() + "%" : null, commercialPropEntity.partner.lower()::like)
                        .add(filter.getTimestamp(), commercialPropEntity.timestamp::eq)
                        .buildAnd());

    }
}
