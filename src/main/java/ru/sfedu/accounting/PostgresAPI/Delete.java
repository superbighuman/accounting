package ru.sfedu.accounting.PostgresAPI;

import ru.sfedu.accounting.Models.Model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class Delete extends PostgresBaseClass implements IDelete{
    protected String DELETE_QUERY = "DELETE FROM RELATION WHERE ";
    public Delete(String relation) {
        super(relation);
        DELETE_QUERY = DELETE_QUERY.replace(RELATION_PLACE_HOLDER, relation);
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
    public boolean deleteRecord(Model model) {
        String newQuery = prepareQueryString(model);
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        try {
            statement.execute(newQuery);
            return true;
        }
        catch (SQLException e){
            logger.info(e);
            return false;
        }
    }
    protected String prepareQueryString(Model model){
        String key = model.keyGet();
        Map<String, String> map = model.getItems();
        String newQuery = DELETE_QUERY + key + "=" + map.get(key);
        return newQuery;
    }
}
