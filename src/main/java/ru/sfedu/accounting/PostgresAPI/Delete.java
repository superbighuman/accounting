package ru.sfedu.accounting.PostgresAPI;

import java.sql.SQLException;
import java.sql.Statement;

public class Delete implements IDelete{
    @Override
    public boolean dropTable(String table) {
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        String query = String.format("drop table if exists %s", table);
        try {
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean truncateTable(String table) {
        return false;
    }

    @Override
    public boolean deleteRecord(String table, String key) {
        return false;
    }
}
