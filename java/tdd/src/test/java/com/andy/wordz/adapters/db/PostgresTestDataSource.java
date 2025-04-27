package com.andy.wordz.adapters.db;

import org.postgresql.ds.PGSimpleDataSource;

public class PostgresTestDataSource extends PGSimpleDataSource {
    PostgresTestDataSource() {
        setServerNames(new String[]{"localhost"});
        setCurrentSchema("public");
        setDatabaseName("postgres");
        setUser("postgres");
        setPassword("postgres");
    }
}
