package ru.sfedu.accounting.FileDB;

import org.apache.log4j.Logger;

public interface IWrite {
    static Logger logger = Logger.getLogger(IWrite.class);
    public boolean insertRecord(String tableName, String[] values);
}
