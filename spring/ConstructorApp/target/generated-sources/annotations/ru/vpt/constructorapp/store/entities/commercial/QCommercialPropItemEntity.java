package ru.vpt.constructorapp.store.entities.commercial;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommercialPropItemEntity is a Querydsl query type for CommercialPropItemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommercialPropItemEntity extends EntityPathBase<CommercialPropItemEntity> {

    private static final long serialVersionUID = -2099441905L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommercialPropItemEntity commercialPropItemEntity = new QCommercialPropItemEntity("commercialPropItemEntity");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final QCommercialPropEntity commercialProp;

    public final NumberPath<Long> idCommercialPropItem = createNumber("idCommercialPropItem", Long.class);

    public final ru.vpt.constructorapp.store.entities.product.QProductEntity product;

    public QCommercialPropItemEntity(String variable) {
        this(CommercialPropItemEntity.class, forVariable(variable), INITS);
    }

    public QCommercialPropItemEntity(Path<? extends CommercialPropItemEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommercialPropItemEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommercialPropItemEntity(PathMetadata metadata, PathInits inits) {
        this(CommercialPropItemEntity.class, metadata, inits);
    }

    public QCommercialPropItemEntity(Class<? extends CommercialPropItemEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commercialProp = inits.isInitialized("commercialProp") ? new QCommercialPropEntity(forProperty("commercialProp"), inits.get("commercialProp")) : null;
        this.product = inits.isInitialized("product") ? new ru.vpt.constructorapp.store.entities.product.QProductEntity(forProperty("product"), inits.get("product")) : null;
    }

}

