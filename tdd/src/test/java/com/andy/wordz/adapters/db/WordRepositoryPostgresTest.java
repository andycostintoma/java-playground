package com.andy.wordz.adapters.db;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@DBRider
public class WordRepositoryPostgresTest {
    private DataSource dataSource;
    private final ConnectionHolder connectionHolder = () -> dataSource.getConnection();

    @BeforeEach
    void beforeEachTest() {
        var ds = new PGSimpleDataSource();
        ds.setServerNames(new String[]{"localhost"});
        ds.setDatabaseName("wordzdb");
        ds.setUser("ciuser");
        ds.setPassword("cipassword");

        this.dataSource = ds;
    }

    @Test
    @DataSet("adapters/data/wordTable.json")
    public void fetchesWord() {
        var adapter = new WordRepositoryPostgres(dataSource);

        String actual = adapter.fetchWordByNumber(27);

        assertThat(actual).isEqualTo("ARISE");
    }
}
