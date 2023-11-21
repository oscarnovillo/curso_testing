package dao;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import common.config.Configuracion;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author oscar
 */
@Singleton
public class DBConnectionPool {

    private final DataSource hirakiDatasource;

    private final Configuracion config;

    @Inject
    public DBConnectionPool(Configuracion config) {

        this.config = config;
        hirakiDatasource = getDataSourceHikari();

    }


    public Connection getConnection() throws Exception {

        Connection connection;

        connection = hirakiDatasource.getConnection();

        return connection;
    }


    private DataSource getDataSourceHikari() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(this.config.getRuta());
        config.setUsername(this.config.getUser());
        config.setPassword(this.config.getPassword());
        config.setDriverClassName(this.config.getDriver());
        config.setMaximumPoolSize(5);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }

    public DataSource getDataSource() {

        return hirakiDatasource;
    }

    public void cerrarConexion(Connection connection) {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void closePool() {
        ((HikariDataSource) hirakiDatasource).close();
    }

}

