package ru.vpt.constructorapp.store.entities.commercial;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaymentTermsEntity is a Querydsl query type for PaymentTermsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentTermsEntity extends EntityPathBase<PaymentTermsEntity> {

    private static final long serialVersionUID = 2121254896L;

    public static final QPaymentTermsEntity paymentTermsEntity = new QPaymentTermsEntity("paymentTermsEntity");

    public final ListPath<CommercialPropTermsEntity, QCommercialPropTermsEntity> commercialPropTermsEntities = this.<CommercialPropTermsEntity, QCommercialPropTermsEntity>createList("commercialPropTermsEntities", CommercialPropTermsEntity.class, QCommercialPropTermsEntity.class, PathInits.DIRECT2);

    public final StringPath fullName = createString("fullName");

    public final NumberPath<Long> idPaymentTerms = createNumber("idPaymentTerms", Long.class);

    public final StringPath visibleName = createString("visibleName");

    public QPaymentTermsEntity(String variable) {
        super(PaymentTermsEntity.class, forVariable(variable));
    }

    public QPaymentTermsEntity(Path<? extends PaymentTermsEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentTermsEntity(PathMetadata metadata) {
        super(PaymentTermsEntity.class, metadata);
    }

}

