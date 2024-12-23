package ru.sfedu.accounting.FileDB;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public interface IRead {
    static Logger logger = Logger.getLogger(IRead.class);
    public List<String> readAttrs(String tableName);
    public List<String[]> readLines(String tableName);
    public ArrayList<String> readLine(String tableName, String id);
}
