package ru.sfedu.accounting.PostgresAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class Read extends PostgresBaseClass implements IRead {
    protected String query = "SELECT ... FROM RELATION";
    public Read(String relation) {
        super(relation);
        query = query.replace(RELATION_PLACE_HOLDER, relation);
    }

    @Override
    public Optional<ResultSet> selectAll() {
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        String allAttributes = "*";
        String newQuery = query.replace(ATTRIBUTES_PLACE_HOLDER, allAttributes);
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(newQuery);
            return Optional.of(resultSet);
        } catch (SQLException e) {
            logger.info(e);
            return Optional.empty();
        }

    }

    @Override
    public ResultSet select(String attr) {
        return null;
    }

    @Override
    public ResultSet select(List<String> attrs) {
        return null;
    }
}
