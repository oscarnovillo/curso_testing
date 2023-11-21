package dao;

import jakarta.enterprise.inject.Produces;

import javax.sql.DataSource;

public class ProducerDataSource {


    @Produces
    public DataSource getDataSource(DBConnectionPool dbConnectionPool) {
        return dbConnectionPool.getDataSource();
    }
}
