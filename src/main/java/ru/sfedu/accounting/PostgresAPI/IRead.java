package ru.sfedu.accounting.PostgresAPI;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface IRead {
    Logger logger = Logger.getLogger(IRead.class);
    public Optional<ResultSet> selectAll();
    public ResultSet select( String attr);
    public ResultSet select( List<String> attrs);
}
