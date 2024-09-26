package ru.vpt.constructorapp.store.entities.reducer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReducerTypeEntity is a Querydsl query type for ReducerTypeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReducerTypeEntity extends EntityPathBase<ReducerTypeEntity> {

    private static final long serialVersionUID = 176579375L;

    public static final QReducerTypeEntity reducerTypeEntity = new QReducerTypeEntity("reducerTypeEntity");

    public final NumberPath<Long> idReducerType = createNumber("idReducerType", Long.class);

    public final StringPath reducerTypeName = createString("reducerTypeName");

    public QReducerTypeEntity(String variable) {
        super(ReducerTypeEntity.class, forVariable(variable));
    }

    public QReducerTypeEntity(Path<? extends ReducerTypeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReducerTypeEntity(PathMetadata metadata) {
        super(ReducerTypeEntity.class, metadata);
    }

}

