package ru.sfedu.accounting.FileDB;

import org.apache.log4j.Logger;

public interface IDelete {
    static Logger logger = Logger.getLogger(IDelete.class);
    public boolean deleteRecord(String tableName, String id);
    public boolean dropTable(String tableName);
}
