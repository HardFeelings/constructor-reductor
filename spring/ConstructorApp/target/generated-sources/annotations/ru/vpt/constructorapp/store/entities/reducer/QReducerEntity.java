package ru.vpt.constructorapp.store.entities.reducer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReducerEntity is a Querydsl query type for ReducerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReducerEntity extends EntityPathBase<ReducerEntity> {

    private static final long serialVersionUID = 890389589L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReducerEntity reducerEntity = new QReducerEntity("reducerEntity");

    public final NumberPath<Integer> diameterOutputShaft = createNumber("diameterOutputShaft", Integer.class);

    public final NumberPath<Long> idReducer = createNumber("idReducer", Long.class);

    public final NumberPath<Double> ratio = createNumber("ratio", Double.class);

    public final QReducerInputTypeEntity reducerInputType;

    public final QReducerInstallationTypeEntity reducerInstallationType;

    public final QReducerMountingEntity reducerMounting;

    public final QReducerOutputShaftTypeEntity reducerOutputShaftType;

    public final QReducerSizeEntity reducerSize;

    public final QReducerTypeEntity reducerType;

    public QReducerEntity(String variable) {
        this(ReducerEntity.class, forVariable(variable), INITS);
    }

    public QReducerEntity(Path<? extends ReducerEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReducerEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReducerEntity(PathMetadata metadata, PathInits inits) {
        this(ReducerEntity.class, metadata, inits);
    }

    public QReducerEntity(Class<? extends ReducerEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reducerInputType = inits.isInitialized("reducerInputType") ? new QReducerInputTypeEntity(forProperty("reducerInputType"), inits.get("reducerInputType")) : null;
        this.reducerInstallationType = inits.isInitialized("reducerInstallationType") ? new QReducerInstallationTypeEntity(forProperty("reducerInstallationType"), inits.get("reducerInstallationType")) : null;
        this.reducerMounting = inits.isInitialized("reducerMounting") ? new QReducerMountingEntity(forProperty("reducerMounting")) : null;
        this.reducerOutputShaftType = inits.isInitialized("reducerOutputShaftType") ? new QReducerOutputShaftTypeEntity(forProperty("reducerOutputShaftType"), inits.get("reducerOutputShaftType")) : null;
        this.reducerSize = inits.isInitialized("reducerSize") ? new QReducerSizeEntity(forProperty("reducerSize"), inits.get("reducerSize")) : null;
        this.reducerType = inits.isInitialized("reducerType") ? new QReducerTypeEntity(forProperty("reducerType")) : null;
    }

}

