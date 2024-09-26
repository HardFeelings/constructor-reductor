package ru.vpt.constructorapp.store.entities.reducer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReducerInputTypeEntity is a Querydsl query type for ReducerInputTypeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReducerInputTypeEntity extends EntityPathBase<ReducerInputTypeEntity> {

    private static final long serialVersionUID = -1704391947L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReducerInputTypeEntity reducerInputTypeEntity = new QReducerInputTypeEntity("reducerInputTypeEntity");

    public final NumberPath<Long> idReducerInputType = createNumber("idReducerInputType", Long.class);

    public final StringPath reducerInputTypeValue = createString("reducerInputTypeValue");

    public final QReducerTypeEntity reducerType;

    public QReducerInputTypeEntity(String variable) {
        this(ReducerInputTypeEntity.class, forVariable(variable), INITS);
    }

    public QReducerInputTypeEntity(Path<? extends ReducerInputTypeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReducerInputTypeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReducerInputTypeEntity(PathMetadata metadata, PathInits inits) {
        this(ReducerInputTypeEntity.class, metadata, inits);
    }

    public QReducerInputTypeEntity(Class<? extends ReducerInputTypeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reducerType = inits.isInitialized("reducerType") ? new QReducerTypeEntity(forProperty("reducerType")) : null;
    }

}

