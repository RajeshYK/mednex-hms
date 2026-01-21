package com.mednex.hms.tenant;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MultiTenantConnectionProviderImpl
        implements MultiTenantConnectionProvider {

    private static final long serialVersionUID = 1L;

    private final DataSource dataSource;

    public MultiTenantConnectionProviderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Connection getConnection(Object tenantIdentifier) throws SQLException {
        Connection connection = getAnyConnection();

        String schema = tenantIdentifier != null
                ? tenantIdentifier.toString()
                : "public";

        System.out.println(">>> SETTING SCHEMA = " + schema);

        // ✅ GUARANTEED schema switch
        connection.createStatement()
                  .execute("SET search_path TO " + schema);

        return connection;
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public void releaseConnection(Object tenantIdentifier, Connection connection)
            throws SQLException {

        // reset to public to avoid tenant leak
        connection.createStatement()
                  .execute("SET search_path TO public");

        connection.close();
    }
    
    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class<?> unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }
}
