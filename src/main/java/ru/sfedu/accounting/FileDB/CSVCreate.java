package ru.sfedu.accounting.FileDB;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCreate implements ICreate{
    public boolean createTable(String name, String[] attrs){
        String path = "src/main/resources";
        String newPath = path + "/" + name + ".csv";
        FileWriter fileWriter;
        try {
            File fileChecker = new File(newPath);
            if(!fileChecker.exists())
                fileWriter = new FileWriter(newPath);
            else{
                IOException alreadyExist = new IOException(name);
                logger.info(alreadyExist);
                return false;
            }
        }
        catch (Exception e){
            logger.info(e);
            return false;
        }
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        csvWriter.writeNext(attrs);
        try {
            csvWriter.close();
        } catch (IOException e) {
            logger.info(e);
            return false;
        }
        return true;
    }
}
