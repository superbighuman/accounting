package ru.sfedu.accounting.FileDB;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWrite implements IWrite{
    @Override
    public boolean insertRecord(String tableName, String[] values ) {

        if (!TableChecker.CSVtableExist(tableName)){
            logger.info(new IOException(tableName));
            return false;
        }
        String CSVtablePath;
        CSVWriter csvWriter;
        try {
            CSVtablePath = TableChecker.CSVtablePath(tableName);
        } catch (IOException e) {
            logger.info(e);
            return false;
        }
        try {
            csvWriter = new CSVWriter(new FileWriter(CSVtablePath,true));
        } catch (IOException e) {
            logger.info(e);
            return false;
        }
        if(values.length == new CSVRead().readAttrs(tableName).size()){
            csvWriter.writeNext(values);
            try {
                csvWriter.close();
            } catch (IOException e) {
                logger.info(e);
                return false;
            }
            return true;
        }
        else{
            logger.info("количество атрибутов не совпадает");
            return false;
        }

    }

}
