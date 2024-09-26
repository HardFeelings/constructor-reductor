package ru.vpt.constructorapp.store.entities.commercial;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = 2103918394L;

    public static final QEmployee employee = new QEmployee("employee");

    public final NumberPath<Long> idEmployee = createNumber("idEmployee", Long.class);

    public final StringPath login = createString("login");

    public final StringPath password = createString("password");

    public final CollectionPath<Role, QRole> roles = this.<Role, QRole>createCollection("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public QEmployee(String variable) {
        super(Employee.class, forVariable(variable));
    }

    public QEmployee(Path<? extends Employee> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployee(PathMetadata metadata) {
        super(Employee.class, metadata);
    }

}

