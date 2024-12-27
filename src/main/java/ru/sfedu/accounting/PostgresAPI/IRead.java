package ru.sfedu.accounting.PostgresAPI;

import java.sql.ResultSet;
import java.util.List;

public interface IRead {
    public ResultSet selectAll(String relation);
    public ResultSet select(String relation, String attr);
    public ResultSet select(String relation, List<String> attrs);
}
