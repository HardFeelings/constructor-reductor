package ru.vpt.constructorapp.store.entities.motor;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMotorTypeEntity is a Querydsl query type for MotorTypeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMotorTypeEntity extends EntityPathBase<MotorTypeEntity> {

    private static final long serialVersionUID = 135843521L;

    public static final QMotorTypeEntity motorTypeEntity = new QMotorTypeEntity("motorTypeEntity");

    public final NumberPath<Long> idMotorType = createNumber("idMotorType", Long.class);

    public final StringPath motorTypeName = createString("motorTypeName");

    public QMotorTypeEntity(String variable) {
        super(MotorTypeEntity.class, forVariable(variable));
    }

    public QMotorTypeEntity(Path<? extends MotorTypeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMotorTypeEntity(PathMetadata metadata) {
        super(MotorTypeEntity.class, metadata);
    }

}

