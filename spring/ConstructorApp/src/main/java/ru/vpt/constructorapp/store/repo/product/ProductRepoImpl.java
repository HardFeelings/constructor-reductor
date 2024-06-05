package ru.vpt.constructorapp.store.repo.product;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ru.vpt.constructorapp.api.filter.dto.FilterDto;
import ru.vpt.constructorapp.api.util.QPredicates;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

import java.util.List;

import static ru.vpt.constructorapp.store.entities.product.QProductEntity.productEntity;
@RequiredArgsConstructor
public class ProductRepoImpl implements ProductCustomRepo{
    private final EntityManager entityManager;
    @Override
    public List<ProductEntity> findByFilter(FilterDto filter) {
        return new JPAQuery<ProductEntity>(entityManager)
                .select(productEntity)
                .from(productEntity)
                .where(QPredicates.builder()
                        .add(filter.getProductTypeId(), productEntity.productType.idProductType::eq)
                        .add(filter.getProductOptions(), productEntity.options.any().idProductOption::in)
                        .add(filter.getMotorTypeId(), productEntity.motor.idMotor::eq)
                        .add(filter.getMotorAdapterTypeId(), productEntity.motor.motorAdapterType.idMotorAdapterType::eq)
                        .add(filter.getPower(), productEntity.motor.power::eq)
                        .add(filter.getFrequency(), productEntity.motor.frequency::eq)
                        .add(filter.getRpm(), productEntity.motor.frequency::eq)
                        .add(filter.getIdReducerType(), productEntity.reducer.reducerType.idReducerType::eq)
                        .add(filter.getIdReducerSize(), productEntity.reducer.reducerSize.idReducerSize::eq)
                        .add(filter.getDiamInput() - filter.getDiamInputAllowance(),productEntity.reducer.diameterInputShaft::goe)
                        .add(filter.getDiamInput() + filter.getDiamInputAllowance(),productEntity.reducer.diameterInputShaft::loe)
                        .add(filter.getDiamOutput() - filter.getDiamOutputAllowance(),productEntity.reducer.diameterOutputShaft::goe)
                        .add(filter.getDiamOutput() + filter.getDiamOutputAllowance(),productEntity.reducer.diameterOutputShaft::loe)
                        .add(filter.getIdReducerInputType(), productEntity.reducer.reducerInputType.idReducerInputType::eq)
                        .add(filter.getIdReducerAdapterInputType(), productEntity.reducer.reducerAdapterType.idReducerAdapterType::eq)
                        .add(filter.getIdReducerOutputShaftType(), productEntity.reducer.reducerOutputShaftType.idReducerOutputShaftType::eq)
                        .add(filter.getTorqueMoment(), productEntity.reducer.torqueMoment::eq)
                        .add(filter.getRatio(), productEntity.reducer.ratio::eq)
                        .add(filter.getIdReducerInstallationType(), productEntity.reducer.reducerInstallationType.idReducerInstallationType::eq)
                        .add(filter.getIdReducerMounting(), productEntity.reducer.reducerMounting.idReducerMounting::eq)
                        .buildAnd())
                .fetch();
    }
}
