//package com.project.springbootwebstore.model.repository;
//
//import org.hibernate.dialect.PostgreSQL91Dialect;
//import org.hibernate.dialect.PostgreSQL95Dialect;
//import org.hibernate.dialect.function.AnsiTrimFunction;
//import org.hibernate.dialect.function.SQLFunctionTemplate;
//import org.hibernate.dialect.function.StandardSQLFunction;
//import org.hibernate.dialect.function.VarArgsSQLFunction;
//import org.hibernate.dialect.identity.PostgreSQL10IdentityColumnSupport;
//import org.hibernate.type.StandardBasicTypes;
//
//public class MyPostgreSQLDialect extends PostgreSQL95Dialect {
//
//    public MyPostgreSQLDialect() {
//        super();
//        registerFunction("plainto_tsquery", new StandardSQLFunction("plainto_tsquery", StandardBasicTypes.STRING));
//        registerFunction( "custom_textsearch", new VarArgsSQLFunction(StandardBasicTypes.BOOLEAN, "to_tsvector(", ") @@ to_tsquery(", ")"));
//        registerFunction("fts", new SQLFunctionTemplate(StandardBasicTypes.BOOLEAN,
//                "document @@ plainto_tsquery(?1)"));
//    }
//}