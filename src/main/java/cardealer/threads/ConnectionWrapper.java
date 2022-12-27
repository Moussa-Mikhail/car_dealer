package cardealer.threads;

class ConnectionWrapper implements IConnection {
    private ConnectionPool connectionPool;
    private IConnection connection;

    ConnectionWrapper(ConnectionPool connectionPool, IConnection connection) {
        this.connectionPool = connectionPool;
        this.connection = connection;
    }

    @Override
    public void close() {
        connectionPool.releaseConnection(connection);
    }
}
