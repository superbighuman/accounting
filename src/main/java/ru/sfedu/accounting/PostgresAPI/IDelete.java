package ru.sfedu.accounting.PostgresAPI;

import org.apache.log4j.Logger;

public interface IDelete {
    Logger logger = Logger.getLogger(IDelete.class);
    public boolean dropTable();
    public boolean truncateTable();
    public boolean deleteRecord( String key);
}
