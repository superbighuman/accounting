package ru.sfedu.accounting.PostgresAPI;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface IRead {
    Logger logger = Logger.getLogger(IRead.class);
    public Optional<ResultSet> selectAll();
    public Optional<ResultSet> select( String attr);
    public Optional<ResultSet> select( List<String> attrs);
    public Optional<ResultSet> where(String attr, String key, Object whereValue);
    public Optional<ResultSet> where(List<String> attrs, String key, Object whereValue);
}
