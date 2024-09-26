package ru.vpt.constructorapp.store.entities.commercial;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommercialPropEntity is a Querydsl query type for CommercialPropEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommercialPropEntity extends EntityPathBase<CommercialPropEntity> {

    private static final long serialVersionUID = 661292700L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommercialPropEntity commercialPropEntity = new QCommercialPropEntity("commercialPropEntity");

    public final ListPath<CommercialPropItemEntity, QCommercialPropItemEntity> commercialPropItems = this.<CommercialPropItemEntity, QCommercialPropItemEntity>createList("commercialPropItems", CommercialPropItemEntity.class, QCommercialPropItemEntity.class, PathInits.DIRECT2);

    public final ListPath<CommercialPropTermsEntity, QCommercialPropTermsEntity> commercialPropTerms = this.<CommercialPropTermsEntity, QCommercialPropTermsEntity>createList("commercialPropTerms", CommercialPropTermsEntity.class, QCommercialPropTermsEntity.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> cost = createNumber("cost", java.math.BigDecimal.class);

    public final StringPath deliveryTerms = createString("deliveryTerms");

    public final NumberPath<Integer> deliveryTime = createNumber("deliveryTime", Integer.class);

    public final NumberPath<Integer> guarantee = createNumber("guarantee", Integer.class);

    public final NumberPath<Long> idCommercialProp = createNumber("idCommercialProp", Long.class);

    public final QManagerEntity manager;

    public final NumberPath<Double> marginRatio = createNumber("marginRatio", Double.class);

    public final StringPath number = createString("number");

    public final StringPath partner = createString("partner");

    public final DateTimePath<String> timestamp = createDateTime("timestamp", String.class);

    public QCommercialPropEntity(String variable) {
        this(CommercialPropEntity.class, forVariable(variable), INITS);
    }

    public QCommercialPropEntity(Path<? extends CommercialPropEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommercialPropEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommercialPropEntity(PathMetadata metadata, PathInits inits) {
        this(CommercialPropEntity.class, metadata, inits);
    }

    public QCommercialPropEntity(Class<? extends CommercialPropEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.manager = inits.isInitialized("manager") ? new QManagerEntity(forProperty("manager")) : null;
    }

}

