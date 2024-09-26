package ru.vpt.constructorapp.store.entities.reducer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReducerOutputShaftTypeEntity is a Querydsl query type for ReducerOutputShaftTypeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReducerOutputShaftTypeEntity extends EntityPathBase<ReducerOutputShaftTypeEntity> {

    private static final long serialVersionUID = 1532571620L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReducerOutputShaftTypeEntity reducerOutputShaftTypeEntity = new QReducerOutputShaftTypeEntity("reducerOutputShaftTypeEntity");

    public final NumberPath<Long> idReducerOutputShaftType = createNumber("idReducerOutputShaftType", Long.class);

    public final StringPath reducerOutputShaftTypeValue = createString("reducerOutputShaftTypeValue");

    public final QReducerTypeEntity reducerType;

    public QReducerOutputShaftTypeEntity(String variable) {
        this(ReducerOutputShaftTypeEntity.class, forVariable(variable), INITS);
    }

    public QReducerOutputShaftTypeEntity(Path<? extends ReducerOutputShaftTypeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReducerOutputShaftTypeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReducerOutputShaftTypeEntity(PathMetadata metadata, PathInits inits) {
        this(ReducerOutputShaftTypeEntity.class, metadata, inits);
    }

    public QReducerOutputShaftTypeEntity(Class<? extends ReducerOutputShaftTypeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reducerType = inits.isInitialized("reducerType") ? new QReducerTypeEntity(forProperty("reducerType")) : null;
    }

}

