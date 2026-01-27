package com.mednex.hms.tenant;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

        String schema = (tenantIdentifier != null)
                ? tenantIdentifier.toString()
                : "public";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SET search_path TO " + schema);
            System.out.println(">>> SET search_path TO " + schema);
        }

        return connection;
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public void releaseConnection(Object tenantIdentifier, Connection connection)
            throws SQLException {

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SET search_path TO public");
        }

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
