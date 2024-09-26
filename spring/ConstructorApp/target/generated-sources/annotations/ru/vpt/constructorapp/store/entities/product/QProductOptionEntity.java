package ru.vpt.constructorapp.store.entities.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductOptionEntity is a Querydsl query type for ProductOptionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductOptionEntity extends EntityPathBase<ProductOptionEntity> {

    private static final long serialVersionUID = 530501104L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductOptionEntity productOptionEntity = new QProductOptionEntity("productOptionEntity");

    public final NumberPath<Long> idProductOption = createNumber("idProductOption", Long.class);

    public final StringPath productOptionValue = createString("productOptionValue");

    public final QProductTypeEntity productType;

    public QProductOptionEntity(String variable) {
        this(ProductOptionEntity.class, forVariable(variable), INITS);
    }

    public QProductOptionEntity(Path<? extends ProductOptionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductOptionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductOptionEntity(PathMetadata metadata, PathInits inits) {
        this(ProductOptionEntity.class, metadata, inits);
    }

    public QProductOptionEntity(Class<? extends ProductOptionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productType = inits.isInitialized("productType") ? new QProductTypeEntity(forProperty("productType")) : null;
    }

}

