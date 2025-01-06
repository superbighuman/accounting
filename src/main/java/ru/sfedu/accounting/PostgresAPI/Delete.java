package ru.sfedu.accounting.PostgresAPI;

import java.sql.SQLException;
import java.sql.Statement;

public class Delete extends PostgresBaseClass implements IDelete{
    public Delete(String relation) {
        super(relation);
    }

    @Override
    public boolean dropTable() {
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        String query = String.format("drop table if exists %s", relation);
        try {
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean truncateTable() {
        return false;
    }

    @Override
    public boolean deleteRecord(String key) {
        return false;
    }
}
