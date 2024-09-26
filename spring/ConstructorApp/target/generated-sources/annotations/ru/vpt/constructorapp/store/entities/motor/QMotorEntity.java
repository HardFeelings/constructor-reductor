package ru.vpt.constructorapp.store.entities.motor;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMotorEntity is a Querydsl query type for MotorEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMotorEntity extends EntityPathBase<MotorEntity> {

    private static final long serialVersionUID = 1069369575L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMotorEntity motorEntity = new QMotorEntity("motorEntity");

    public final NumberPath<Double> efficiency = createNumber("efficiency", Double.class);

    public final NumberPath<Double> frequency = createNumber("frequency", Double.class);

    public final NumberPath<Long> idMotor = createNumber("idMotor", Long.class);

    public final NumberPath<Double> momentOfInertia = createNumber("momentOfInertia", Double.class);

    public final QMotorAdapterTypeEntity motorAdapterType;

    public final QMotorTypeEntity motorType;

    public final NumberPath<Double> power = createNumber("power", Double.class);

    public final NumberPath<Double> ratedCurrent = createNumber("ratedCurrent", Double.class);

    public QMotorEntity(String variable) {
        this(MotorEntity.class, forVariable(variable), INITS);
    }

    public QMotorEntity(Path<? extends MotorEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMotorEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMotorEntity(PathMetadata metadata, PathInits inits) {
        this(MotorEntity.class, metadata, inits);
    }

    public QMotorEntity(Class<? extends MotorEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.motorAdapterType = inits.isInitialized("motorAdapterType") ? new QMotorAdapterTypeEntity(forProperty("motorAdapterType"), inits.get("motorAdapterType")) : null;
        this.motorType = inits.isInitialized("motorType") ? new QMotorTypeEntity(forProperty("motorType")) : null;
    }

}

