package ru.sfedu.accounting.PostgresAPI;
import org.apache.log4j.Logger;

import java.sql.*;
public interface IPSQLConn {
    Logger logger = Logger.getLogger(IPSQLConn.class);
    public Connection getConnection();
    public Statement getStatement() throws SQLException;
    public Statement getStatement(Connection conn);
    public boolean closeConnection(Connection conn);
}
