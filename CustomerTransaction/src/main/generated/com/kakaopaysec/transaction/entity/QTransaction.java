package com.kakaopaysec.transaction.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTransaction is a Querydsl query type for Transaction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransaction extends EntityPathBase<Transaction> {

    private static final long serialVersionUID = 921951698L;

    public static final QTransaction transaction = new QTransaction("transaction");

    public final StringPath accountNo = createString("accountNo");

    public final ComparablePath<Character> cancleYn = createComparable("cancleYn", Character.class);

    public final NumberPath<Long> trAmount = createNumber("trAmount", Long.class);

    public final StringPath trDate = createString("trDate");

    public final NumberPath<Long> trFee = createNumber("trFee", Long.class);

    public final NumberPath<Integer> trNo = createNumber("trNo", Integer.class);

    public QTransaction(String variable) {
        super(Transaction.class, forVariable(variable));
    }

    public QTransaction(Path<? extends Transaction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTransaction(PathMetadata metadata) {
        super(Transaction.class, metadata);
    }

}

