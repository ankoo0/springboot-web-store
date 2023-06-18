package com.project.springbootwebstore.repository.sql;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;

import java.util.List;

public class PgFullTextFunction implements SQLFunction {

    /* Column name of TSVECTOR field in PgSQL table */
    public static final String FTS_VECTOR_FIELD = "document";

    @Override
    public Type getReturnType(Type columnType, Mapping mapping)
            throws QueryException {
        return new BooleanType();
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public boolean hasParenthesesIfNoArguments() {
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public String render(Type type, List args, SessionFactoryImplementor factory) throws QueryException {
        String searchString = (String) args.get(0);

        System.out.println(FTS_VECTOR_FIELD + " @@ plainto_tsquery(" + searchString + ")");

        return FTS_VECTOR_FIELD + " @@ plainto_tsquery(" + searchString + ")";
    }
}