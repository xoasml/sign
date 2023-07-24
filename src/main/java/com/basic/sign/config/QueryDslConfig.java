package com.basic.sign.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SchemaAndTable;
import com.querydsl.sql.namemapping.NameMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class QueryDslConfig {


    private final EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(new MySQLTemplates());

        configuration.setDynamicNameMapping(new NameMapping() {
            @Override
            public Optional<String> getColumnOverride(SchemaAndTable key, String column) {
                return Optional.empty();
            }

            @Override
            public Optional<SchemaAndTable> getOverride(SchemaAndTable key) {
                return Optional.of(new SchemaAndTable(camelCaseToSnakeCase(key.getSchema()), camelCaseToSnakeCase(key.getTable())));
            }
        });
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        return jpaQueryFactory;
    }

    private String camelCaseToSnakeCase(String camel) {
        return camel.replaceAll("([A-Z])", "_$1").toLowerCase();
    }


}
