package ru.vpt.constructorapp.store.entities.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductEntity is a Querydsl query type for ProductEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductEntity extends EntityPathBase<ProductEntity> {

    private static final long serialVersionUID = -16019685L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductEntity productEntity = new QProductEntity("productEntity");

    public final ListPath<ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity, ru.vpt.constructorapp.store.entities.commercial.QCommercialPropItemEntity> commercialPropItems = this.<ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity, ru.vpt.constructorapp.store.entities.commercial.QCommercialPropItemEntity>createList("commercialPropItems", ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity.class, ru.vpt.constructorapp.store.entities.commercial.QCommercialPropItemEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> idProduct = createNumber("idProduct", Long.class);

    public final ru.vpt.constructorapp.store.entities.motor.QMotorEntity motor;

    public final StringPath name = createString("name");

    public final SetPath<ProductOptionEntity, QProductOptionEntity> options = this.<ProductOptionEntity, QProductOptionEntity>createSet("options", ProductOptionEntity.class, QProductOptionEntity.class, PathInits.DIRECT2);

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final ArrayPath<byte[], Byte> productImage = createArray("productImage", byte[].class);

    public final QProductTypeEntity productType;

    public final ru.vpt.constructorapp.store.entities.reducer.QReducerEntity reducer;

    public final NumberPath<Double> rpm = createNumber("rpm", Double.class);

    public final NumberPath<Double> serviceFactor = createNumber("serviceFactor", Double.class);

    public final NumberPath<Double> torqueMoment = createNumber("torqueMoment", Double.class);

    public final NumberPath<Double> weight = createNumber("weight", Double.class);

    public QProductEntity(String variable) {
        this(ProductEntity.class, forVariable(variable), INITS);
    }

    public QProductEntity(Path<? extends ProductEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductEntity(PathMetadata metadata, PathInits inits) {
        this(ProductEntity.class, metadata, inits);
    }

    public QProductEntity(Class<? extends ProductEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.motor = inits.isInitialized("motor") ? new ru.vpt.constructorapp.store.entities.motor.QMotorEntity(forProperty("motor"), inits.get("motor")) : null;
        this.productType = inits.isInitialized("productType") ? new QProductTypeEntity(forProperty("productType")) : null;
        this.reducer = inits.isInitialized("reducer") ? new ru.vpt.constructorapp.store.entities.reducer.QReducerEntity(forProperty("reducer"), inits.get("reducer")) : null;
    }

}

