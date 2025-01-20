package ru.sfedu.accounting.PostgresAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class Read extends PostgresBaseClass implements IRead {
    protected String selectQuery = "SELECT ... FROM RELATION";
    protected String whereQuery = "SELECT ... FROM RELATION WHERE where_condition";
    public Read(String relation) {
        super(relation);
        selectQuery = selectQuery.replace(RELATION_PLACE_HOLDER, relation);
        whereQuery = whereQuery.replace(RELATION_PLACE_HOLDER, relation);
    }

    @Override
    public Optional<ResultSet> selectAll() {
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        String allAttributes = "*";
        String newQuery = selectQuery.replace(ATTRIBUTES_PLACE_HOLDER, allAttributes);
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(newQuery);
            psqlConn.closeConnection();
            return Optional.of(resultSet);
        } catch (SQLException e) {
            logger.info(e);
            psqlConn.closeConnection();
            return Optional.empty();
        }
    }
    @Override
    public Optional<ResultSet> select(String attr) {
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        String newQuery = selectQuery.replace(ATTRIBUTES_PLACE_HOLDER, attr);
        try {
            ResultSet resultSet = statement.executeQuery(newQuery);
            psqlConn.closeConnection();
            return Optional.of(resultSet);
        } catch (SQLException e) {
            logger.info(e);
            psqlConn.closeConnection();
            return Optional.empty();
        }
    }
    @Override
    public Optional<ResultSet> select(List<String> attrs) {
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        String newQuery = selectQuery.replace(ATTRIBUTES_PLACE_HOLDER, String.join(", ", attrs));
        try {
            ResultSet resultSet = statement.executeQuery(newQuery);
            psqlConn.closeConnection();
            return Optional.of(resultSet);
        } catch (SQLException e) {
            logger.info(e);
            psqlConn.closeConnection();
            return Optional.empty();
        }
    }
    public Optional<ResultSet> where(String attr, String key, Object whereValue){
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        String newQuery = whereQuery.replace(ATTRIBUTES_PLACE_HOLDER, attr);
        newQuery = newQuery.replace(WHERE_CONDITIONAL, key + "=" + whereValue);
        try {
            ResultSet resultSet = statement.executeQuery(newQuery);
            psqlConn.closeConnection();
            return Optional.of(resultSet);
        } catch (SQLException e) {
            logger.info(e);
            psqlConn.closeConnection();
            return Optional.empty();
        }
    }

    @Override
    public Optional<ResultSet> where(List<String> attrs, String key, Object whereValue) {
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        if (whereValue instanceof String)
            whereValue = "'" + whereValue + "'";
        String newQuery = whereQuery.replace(ATTRIBUTES_PLACE_HOLDER, String.join(", ", attrs));
        newQuery = newQuery.replace(WHERE_CONDITIONAL, key + "=" + whereValue);
        try {
            ResultSet resultSet = statement.executeQuery(newQuery);
            psqlConn.closeConnection();
            return Optional.of(resultSet);
        } catch (SQLException e) {
            logger.info(e);
            psqlConn.closeConnection();
            return Optional.empty();
        }
    }

}
