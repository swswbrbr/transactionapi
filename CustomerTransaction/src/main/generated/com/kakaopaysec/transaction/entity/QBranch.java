package com.kakaopaysec.transaction.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBranch is a Querydsl query type for Branch
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBranch extends EntityPathBase<Branch> {

    private static final long serialVersionUID = -1867115986L;

    public static final QBranch branch = new QBranch("branch");

    public final ComparablePath<Character> brCode = createComparable("brCode", Character.class);

    public final StringPath brName = createString("brName");

    public QBranch(String variable) {
        super(Branch.class, forVariable(variable));
    }

    public QBranch(Path<? extends Branch> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBranch(PathMetadata metadata) {
        super(Branch.class, metadata);
    }

}

