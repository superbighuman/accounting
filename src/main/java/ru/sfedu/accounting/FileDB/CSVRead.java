package ru.sfedu.accounting.FileDB;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVRead implements IRead{
    @Override
    public ArrayList<String> readAttrs(String tableName) {
        ArrayList<String[]> lines = readLines(tableName);
        if(lines.size() > 0){
            ArrayList<String> attrs = new ArrayList<>(List.of(readLines(tableName).get(0)));
            return attrs;
        }
        return new ArrayList<>();

    }

    @Override
    public ArrayList<String[]> readLines(String tableName) {
        String tablePath;
        ArrayList<String[]> lines = new ArrayList<>();
        try {
            tablePath = TableChecker.CSVtablePath(tableName);
        } catch (IOException e) {
            logger.info(e);
            return lines;
        }
        FileReader fileReader;
        try {
            fileReader = new FileReader(tablePath);
        } catch (FileNotFoundException e) {
            logger.info(e);
            return lines;
        }
        CSVReader csvReader = new CSVReader(fileReader);
        try {
            csvReader.readAll().forEach(lines::add);
        } catch (Exception e) {
            logger.info(e);
            return lines;
        }
        return lines;
    }

    @Override
    public ArrayList<String> readLine(String tableName, String id) {
        String tablePath;
        ArrayList<String> line = new ArrayList<>();
        try {
            tablePath = TableChecker.CSVtablePath(tableName);
        } catch (IOException e) {
            logger.info(e);
            return line;
        }
        ArrayList<String[]> lines = readLines(tableName);
        for(String[] elem:lines){
            if (elem.length > 0)
                if (elem[0].equals(id))
                    line = new ArrayList<>(List.of(elem));
        }
        return line;
    }
}
