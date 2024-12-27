package ru.sfedu.accounting.PostgresAPI;

import java.sql.ResultSet;
import java.util.List;

public class Read implements IRead{
    @Override
    public ResultSet selectAll(String relation) {
        return null;
    }

    @Override
    public ResultSet select(String relation, String attr) {
        return null;
    }

    @Override
    public ResultSet select(String relation, List<String> attrs) {
        return null;
    }
}
