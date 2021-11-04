package edu.epam.webproject.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);

    private static final String URL;

    private static final String DB_URL = "db.url";

    private static final String DB_DRIVER = "db.driver";

    private static final Properties properties = new Properties();

    private static final String RESOURCE_FILE = "\\db.properties";

    static {
        String driver = null;
        try(InputStream stream = ConnectionFactory.class.getClassLoader().getResourceAsStream(RESOURCE_FILE)){
            properties.load(stream);
            driver = properties.getProperty(DB_DRIVER);
            Class.forName(driver);

        }catch (ClassNotFoundException e) {
            logger.fatal("Unable to register driver: " + driver);
            throw new RuntimeException("Unable to register driver: \" + driverName", e);
        } catch (IOException e){
            logger.fatal("Unable to find properties file: " + RESOURCE_FILE);
            throw new RuntimeException("Unable to find properties file: " + RESOURCE_FILE, e);
        }
        URL = properties.getProperty(DB_URL);
    }

    static Connection getConnection() {
        Connection connection = null;
        try {
             connection =  DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            logger.error("Unable to create connection");
        }
        return connection;
    }
}
