package ru.vpt.constructorapp.store.entities.reducer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReducerMountingEntity is a Querydsl query type for ReducerMountingEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReducerMountingEntity extends EntityPathBase<ReducerMountingEntity> {

    private static final long serialVersionUID = 2080412894L;

    public static final QReducerMountingEntity reducerMountingEntity = new QReducerMountingEntity("reducerMountingEntity");

    public final NumberPath<Long> idReducerMounting = createNumber("idReducerMounting", Long.class);

    public final StringPath reducerMountingValue = createString("reducerMountingValue");

    public QReducerMountingEntity(String variable) {
        super(ReducerMountingEntity.class, forVariable(variable));
    }

    public QReducerMountingEntity(Path<? extends ReducerMountingEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReducerMountingEntity(PathMetadata metadata) {
        super(ReducerMountingEntity.class, metadata);
    }

}

