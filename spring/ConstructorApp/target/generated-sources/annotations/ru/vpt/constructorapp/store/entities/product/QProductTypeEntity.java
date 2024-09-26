package ru.vpt.constructorapp.store.entities.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductTypeEntity is a Querydsl query type for ProductTypeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductTypeEntity extends EntityPathBase<ProductTypeEntity> {

    private static final long serialVersionUID = 1303436021L;

    public static final QProductTypeEntity productTypeEntity = new QProductTypeEntity("productTypeEntity");

    public final NumberPath<Long> idProductType = createNumber("idProductType", Long.class);

    public final StringPath productTypeValue = createString("productTypeValue");

    public QProductTypeEntity(String variable) {
        super(ProductTypeEntity.class, forVariable(variable));
    }

    public QProductTypeEntity(Path<? extends ProductTypeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductTypeEntity(PathMetadata metadata) {
        super(ProductTypeEntity.class, metadata);
    }

}

