package edu.epam.webproject.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CustomConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static CustomConnectionPool instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ReentrantLock reentrantLock = new ReentrantLock(true);
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> usedConnections;
    private static final int DEFAULT_POOL_SIZE = 4;

    private CustomConnectionPool() { }

    public void init() {
        Properties properties = new Properties();
        try {
            freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
            usedConnections = new LinkedBlockingDeque<>();
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                ProxyConnection proxyConnection = (ProxyConnection) ConnectionFactory.getConnection();
                freeConnections.put(proxyConnection);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); //refactor
        }
    }

    public static CustomConnectionPool getInstance() {
        if (!isCreated.get()) {
            reentrantLock.lock();
            try {
                if (instance == null) {
                    instance = new CustomConnectionPool();
                    isCreated.getAndSet(true);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();;
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws SQLException {
        if (connection.getClass() == ProxyConnection.class) {
            usedConnections.remove(connection);
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            connection.close();
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            deregisterDrivers();
        }
    }

    private void deregisterDrivers() {
        Enumeration<Driver> driverEnumeration = DriverManager.getDrivers();
        while (driverEnumeration.hasMoreElements()){
            Driver driver = driverEnumeration.nextElement();
            try{
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Unable to deregister driver " + driver, e);
            }
        }
        /*DriverManager.getDrivers().asIterator().forEachRemaining(driver-> {
            try{
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Unable to deregister driver " + driver, e);
            }
        });*/
    }
}
