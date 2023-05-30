package com.project.springbootwebstore.model.repository.sql;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.BooleanType;

public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {
    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction("search", new PgFullTextFunction());
        metadataBuilder.applySqlFunction("fts",
                new SQLFunctionTemplate(BooleanType.INSTANCE,
                        "document @@ plainto_tsquery('?1')"));
    }

}
