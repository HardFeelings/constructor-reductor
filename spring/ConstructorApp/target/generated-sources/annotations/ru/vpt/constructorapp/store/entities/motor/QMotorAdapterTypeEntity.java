package ru.vpt.constructorapp.store.entities.motor;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMotorAdapterTypeEntity is a Querydsl query type for MotorAdapterTypeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMotorAdapterTypeEntity extends EntityPathBase<MotorAdapterTypeEntity> {

    private static final long serialVersionUID = -1927052056L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMotorAdapterTypeEntity motorAdapterTypeEntity = new QMotorAdapterTypeEntity("motorAdapterTypeEntity");

    public final NumberPath<Long> idMotorAdapterType = createNumber("idMotorAdapterType", Long.class);

    public final StringPath motorAdapterTypeValue = createString("motorAdapterTypeValue");

    public final QMotorTypeEntity motorType;

    public QMotorAdapterTypeEntity(String variable) {
        this(MotorAdapterTypeEntity.class, forVariable(variable), INITS);
    }

    public QMotorAdapterTypeEntity(Path<? extends MotorAdapterTypeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMotorAdapterTypeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMotorAdapterTypeEntity(PathMetadata metadata, PathInits inits) {
        this(MotorAdapterTypeEntity.class, metadata, inits);
    }

    public QMotorAdapterTypeEntity(Class<? extends MotorAdapterTypeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.motorType = inits.isInitialized("motorType") ? new QMotorTypeEntity(forProperty("motorType")) : null;
    }

}

