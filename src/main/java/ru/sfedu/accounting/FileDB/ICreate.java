package ru.sfedu.accounting.FileDB;

import org.apache.log4j.Logger;
public interface ICreate {
    static Logger logger = Logger.getLogger(ICreate.class);
    public boolean createTable(String name, String[] args);
}
