package ru.sfedu.accounting.PostgresAPI;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.List;

public interface IRead {
    Logger logger = Logger.getLogger(IRead.class);
    public ResultSet selectAll(String relation);
    public ResultSet select(String relation, String attr);
    public ResultSet select(String relation, List<String> attrs);
}
