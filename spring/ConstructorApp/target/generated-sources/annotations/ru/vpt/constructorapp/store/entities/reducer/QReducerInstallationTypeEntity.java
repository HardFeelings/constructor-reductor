package ru.vpt.constructorapp.store.entities.reducer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReducerInstallationTypeEntity is a Querydsl query type for ReducerInstallationTypeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReducerInstallationTypeEntity extends EntityPathBase<ReducerInstallationTypeEntity> {

    private static final long serialVersionUID = -842595831L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReducerInstallationTypeEntity reducerInstallationTypeEntity = new QReducerInstallationTypeEntity("reducerInstallationTypeEntity");

    public final NumberPath<Long> idReducerInstallationType = createNumber("idReducerInstallationType", Long.class);

    public final StringPath reducerInstallationTypeValue = createString("reducerInstallationTypeValue");

    public final QReducerTypeEntity reducerType;

    public QReducerInstallationTypeEntity(String variable) {
        this(ReducerInstallationTypeEntity.class, forVariable(variable), INITS);
    }

    public QReducerInstallationTypeEntity(Path<? extends ReducerInstallationTypeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReducerInstallationTypeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReducerInstallationTypeEntity(PathMetadata metadata, PathInits inits) {
        this(ReducerInstallationTypeEntity.class, metadata, inits);
    }

    public QReducerInstallationTypeEntity(Class<? extends ReducerInstallationTypeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reducerType = inits.isInitialized("reducerType") ? new QReducerTypeEntity(forProperty("reducerType")) : null;
    }

}

