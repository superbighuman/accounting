package ru.sfedu.accounting;


import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class YAMLReader implements YAMLReaderInterface{
    protected String path;
    protected File yaml;
    public YAMLReader(String path){
        this.path = path;

    }
    public Map<String, Object> getContent(){
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(path);
        Map<String, Object> data = yaml.load(inputStream);
        return data;
    }
}
