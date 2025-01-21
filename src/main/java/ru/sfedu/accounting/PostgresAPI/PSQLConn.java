package ru.sfedu.accounting.PostgresAPI;

import ru.sfedu.accounting.Constants.ResourcesConstants;
import ru.sfedu.accounting.YAMLReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

public class PSQLConn implements IPSQLConn{
    Connection conn;
    @Override
    public Connection getConnection() {
        if (this.conn == null) {
            Map<String, Object> keys = new YAMLReader(ResourcesConstants.API_KEYS).getContent();
            String url = (String) keys.get(ResourcesConstants.POSTGRES_HOST);
            String user = (String) keys.get(ResourcesConstants.POSTGRES_USER);
            String password = (String) keys.get(ResourcesConstants.POSTGRES_PASSWORD);
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            Connection conn;
            try {
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                logger.info(e);
                throw new RuntimeException(e);
            }
            this.conn = conn;
        }
        return this.conn;
    }
    public boolean closeConnection(Connection conn) {
        try {
            conn.close();
            return true;
        } catch (SQLException e) {
            logger.info(e);
            return false;
        }
    }
    public boolean closeConnection(){
        return closeConnection(this.conn);
    }
    public Statement getStatement(Connection conn) {
       try {
           return conn.createStatement();
       }
       catch (SQLException e) {
           logger.info(e);
           throw new RuntimeException("Connection closed");
       }
    }
    public Statement getStatement(){
        return getStatement(getConnection());
    }
}
