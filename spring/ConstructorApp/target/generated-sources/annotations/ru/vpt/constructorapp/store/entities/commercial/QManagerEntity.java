package ru.vpt.constructorapp.store.entities.commercial;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QManagerEntity is a Querydsl query type for ManagerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManagerEntity extends EntityPathBase<ManagerEntity> {

    private static final long serialVersionUID = -575420188L;

    public static final QManagerEntity managerEntity = new QManagerEntity("managerEntity");

    public final StringPath email = createString("email");

    public final StringPath fullName = createString("fullName");

    public final NumberPath<Long> idManager = createNumber("idManager", Long.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath position = createString("position");

    public final StringPath shortName = createString("shortName");

    public QManagerEntity(String variable) {
        super(ManagerEntity.class, forVariable(variable));
    }

    public QManagerEntity(Path<? extends ManagerEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QManagerEntity(PathMetadata metadata) {
        super(ManagerEntity.class, metadata);
    }

}

