//package com.project.springbootwebstore.model.repository;
//
//import org.hibernate.boot.MetadataBuilder;
//import org.hibernate.boot.spi.MetadataBuilderContributor;
//import org.hibernate.dialect.function.SQLFunctionTemplate;
//import org.hibernate.type.BooleanType;
//
//public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {
//    @Override
//    public void contribute(MetadataBuilder metadataBuilder) {
//        metadataBuilder.applySqlFunction("fts",
//                new SQLFunctionTemplate(BooleanType.INSTANCE,
//                        "document @@ plainto_tsquery(?1)"));
//    }
//}
