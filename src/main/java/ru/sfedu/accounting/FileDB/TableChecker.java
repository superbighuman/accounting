package ru.sfedu.accounting.FileDB;

import org.apache.log4j.Logger;
import ru.sfedu.accounting.Constants.ResourcesConstants;

import java.io.File;
import java.io.IOException;

public class TableChecker {
    static Logger logger = Logger.getLogger(TableChecker.class);
    public static boolean CSVtableExist(String tableName){
        String tablePath = ResourcesConstants.RESOURCES_PATH+tableName+".csv";
        File fileChecker = new File(tablePath);
        return fileChecker.exists();
    }
    public static String CSVtablePath(String tableName) throws IOException{
        if (!CSVtableExist(tableName)) {
            logger.info(tableName + "is not exist");
            throw new IOException(tableName);
        }
        return ResourcesConstants.RESOURCES_PATH+tableName+".csv";
    }
}
