package ru.vpt.constructorapp.store.repo.product;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.api.filter.dto.FilterDto;
import ru.vpt.constructorapp.api.util.QPredicates;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

import java.util.List;

import static java.util.Optional.ofNullable;
import static ru.vpt.constructorapp.store.entities.product.QProductEntity.productEntity;

@Repository
public class ProductRepoImpl extends QuerydslRepositorySupport implements ProductCustomRepo {

    private final EntityManager entityManager;

    public ProductRepoImpl(EntityManager em) {
        super(ProductEntity.class);
        this.entityManager = em;
    }

    @Override
    public Page<ProductEntity> findByFilter(FilterDto filter, Pageable pageable) {
        Long totalCount = getJPAQuery(filter, productEntity.count()).fetchOne();
        JPAQuery<ProductEntity> query = getJPAQuery(filter, productEntity);
        ofNullable(getQuerydsl()).ifPresent(querydsl -> querydsl.applyPagination(pageable, query));
        List<ProductEntity> pagedData = query.fetch();
        return PageableExecutionUtils.getPage(pagedData, pageable, () -> totalCount);
    }

    private <T> JPAQuery<T> getJPAQuery(FilterDto filter, Expression<T> expression) {
        return new JPAQuery<T>(entityManager)
                .select(expression)
                .from(productEntity)
                .where(QPredicates.builder()
                        .add(filter.getProductTypeId(), productEntity.productType.idProductType::eq)
                        .add(filter.getTorqueMoment(), productEntity.torqueMoment::eq)
                        .add(filter.getRpm() != null ? filter.getRpm() - 5 : null, productEntity.rpm::goe)
                        .add(filter.getRpm() != null ? filter.getRpm() + 5 : null, productEntity.rpm::loe)
                        .add(filter.getMotorTypeId(), productEntity.motor.motorType.idMotorType::eq)
                        .add(filter.getMotorAdapterTypeId(), productEntity.motor.motorAdapterType.idMotorAdapterType::eq)
                        .add(filter.getPower(), productEntity.motor.power::eq)
                        .add(filter.getPolesNumber(), productEntity.motor.polesNumber::eq)
                        .add(filter.getIdReducerType(), productEntity.reducer.reducerType.idReducerType::eq)
                        .add(filter.getIdReducerSize(), productEntity.reducer.reducerSize.idReducerSize::eq)
                        .add(filter.getDiamOutput() == null ? null : filter.getDiamOutput() - filter.getDiamOutputAllowance(), productEntity.reducer.diameterOutputShaft::goe)
                        .add(filter.getDiamOutput() == null ? null : filter.getDiamOutput() + filter.getDiamOutputAllowance(), productEntity.reducer.diameterOutputShaft::loe)
                        .add(filter.getIdReducerInputType(), productEntity.reducer.reducerInputType.idReducerInputType::eq)
                        .add(filter.getIdReducerOutputShaftType(), productEntity.reducer.reducerOutputShaftType.idReducerOutputShaftType::eq)
                        .add(filter.getRatio() != null ? filter.getRatio() - 5 : null, productEntity.reducer.ratio::goe)
                        .add(filter.getRatio() != null ? filter.getRatio() + 5 : null, productEntity.reducer.ratio::loe)
                        .add(filter.getIdReducerInstallationType(), productEntity.reducer.reducerInstallationType.idReducerInstallationType::eq)
                        .add(filter.getIdReducerMounting(), productEntity.reducer.reducerMounting.idReducerMounting::eq)
                        .buildAnd());

    }
}
