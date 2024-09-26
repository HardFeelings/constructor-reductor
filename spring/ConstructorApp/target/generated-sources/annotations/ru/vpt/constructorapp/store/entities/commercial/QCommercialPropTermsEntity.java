package ru.vpt.constructorapp.store.entities.commercial;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommercialPropTermsEntity is a Querydsl query type for CommercialPropTermsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommercialPropTermsEntity extends EntityPathBase<CommercialPropTermsEntity> {

    private static final long serialVersionUID = -1327709903L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommercialPropTermsEntity commercialPropTermsEntity = new QCommercialPropTermsEntity("commercialPropTermsEntity");

    public final QCommercialPropEntity commercialProp;

    public final NumberPath<Integer> days = createNumber("days", Integer.class);

    public final NumberPath<Long> idCommercialPropTerms = createNumber("idCommercialPropTerms", Long.class);

    public final NumberPath<Integer> ord = createNumber("ord", Integer.class);

    public final QPaymentTermsEntity paymentTerms;

    public final NumberPath<Double> percent = createNumber("percent", Double.class);

    public QCommercialPropTermsEntity(String variable) {
        this(CommercialPropTermsEntity.class, forVariable(variable), INITS);
    }

    public QCommercialPropTermsEntity(Path<? extends CommercialPropTermsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommercialPropTermsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommercialPropTermsEntity(PathMetadata metadata, PathInits inits) {
        this(CommercialPropTermsEntity.class, metadata, inits);
    }

    public QCommercialPropTermsEntity(Class<? extends CommercialPropTermsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commercialProp = inits.isInitialized("commercialProp") ? new QCommercialPropEntity(forProperty("commercialProp"), inits.get("commercialProp")) : null;
        this.paymentTerms = inits.isInitialized("paymentTerms") ? new QPaymentTermsEntity(forProperty("paymentTerms")) : null;
    }

}

