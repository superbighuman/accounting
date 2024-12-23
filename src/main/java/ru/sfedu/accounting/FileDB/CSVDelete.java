package ru.sfedu.accounting.FileDB;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CSVDelete implements IDelete{
//TODO: make correct delete
    @Override
    public boolean deleteRecord(String tableName, String id) {
        String tablePath;
        try {
            tablePath = TableChecker.CSVtablePath(tableName);
        } catch (IOException e) {
            logger.info(e);
        }
        ArrayList<String[]> lines = new CSVRead().readLines(tableName);
        for(int i = 0; i < lines.size(); i++){
            if (lines.get(i)[0].equals(id)){
                lines.remove(i);
                break;
            }
        }
        boolean deleted = dropTable(tableName);
        new CSVCreate().createTable(tableName, lines.get(0));
        if (deleted) {
            for (String[] line : lines) {
                new CSVWrite().insertRecord(tableName, line);
            }
            return true;
        }
        else{
            return false;
        }

    }
    public boolean dropTable(String tableName) {
        String tablePath;
        try {
            tablePath = TableChecker.CSVtablePath(tableName);
        } catch (IOException e) {
            logger.info(e);
            return false;
        }

        File table = new File(tablePath);

        if (!table.exists()) {
            return false;
        }
        boolean deleted = table.delete();
        if (deleted) {
            return true;
        } else {
            return false;
        }

    }
}
