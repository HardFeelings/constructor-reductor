package ru.vpt.constructorapp.store.entities.reducer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReducerSizeEntity is a Querydsl query type for ReducerSizeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReducerSizeEntity extends EntityPathBase<ReducerSizeEntity> {

    private static final long serialVersionUID = -524172618L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReducerSizeEntity reducerSizeEntity = new QReducerSizeEntity("reducerSizeEntity");

    public final NumberPath<Long> idReducerSize = createNumber("idReducerSize", Long.class);

    public final StringPath reducerSizeValue = createString("reducerSizeValue");

    public final QReducerTypeEntity reducerType;

    public QReducerSizeEntity(String variable) {
        this(ReducerSizeEntity.class, forVariable(variable), INITS);
    }

    public QReducerSizeEntity(Path<? extends ReducerSizeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReducerSizeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReducerSizeEntity(PathMetadata metadata, PathInits inits) {
        this(ReducerSizeEntity.class, metadata, inits);
    }

    public QReducerSizeEntity(Class<? extends ReducerSizeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reducerType = inits.isInitialized("reducerType") ? new QReducerTypeEntity(forProperty("reducerType")) : null;
    }

}

