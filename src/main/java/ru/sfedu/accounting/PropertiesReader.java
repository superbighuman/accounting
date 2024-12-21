package ru.sfedu.accounting;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class PropertiesReader implements PropertiesInterface{
    String path;
    Logger logger = Logger.getLogger(PropertiesReader.class);
    public PropertiesReader(String path){
        this.path = path;
    }
    public HashMap<String, ArrayList<String>> getContent(){
        File properties = new File(path);
        Scanner sc;
        HashMap<String, ArrayList<String>> content = new HashMap<>();
        try {
            if (this.getClass().getClassLoader().getResourceAsStream(path) != null)
                sc = new Scanner(this.getClass().getClassLoader().getResourceAsStream(path));
            else
                throw new IOException(path);
        } catch (IOException e) {
            logger.info(e);
            return content;
        }
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] splittedLine = line.split("=");
            String key = splittedLine[0];
            ArrayList<String> value = new ArrayList<>(Arrays.asList(splittedLine[1].split(", ")));
            content.put(key, value);
        }
        return content;

    }
}
