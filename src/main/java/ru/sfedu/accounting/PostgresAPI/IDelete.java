package ru.sfedu.accounting.PostgresAPI;

import org.apache.log4j.Logger;

public interface IDelete {
    Logger logger = Logger.getLogger(IDelete.class);
    public boolean dropTable(String table);
    public boolean truncateTable(String table);
    public boolean deleteRecord(String table, String key);
}
