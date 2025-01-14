package ru.sfedu.accounting.Models;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public interface Model{
    Logger logger = Logger.getLogger(Model.class);
    public boolean insertRecord();
    public boolean deleteRecord();
    public boolean updateRecord();
    public boolean exists();
    public default ArrayList<String> getFieldsValues(){
        Class c = this.getClass();
        Field[] fields = c.getDeclaredFields();
        ArrayList<String> result = new ArrayList<>();
        for(Field f: fields)
        {
            String attributeName = f.getName();
            String getterMethodName = "get"
                    + attributeName.substring(0, 1).toUpperCase()
                    + attributeName.substring(1, attributeName.length());
            Method m = null;
            try {
                m = c.getMethod(getterMethodName);
                Object valObject = m.invoke(this);
                if (valObject instanceof String)
                    valObject = "'" + valObject + "'";
                result.add(valObject.toString());
            } catch (Exception e) {
                logger.info(e);
            }
        }
        return result;
    }
    public default ArrayList<String> getFields(){
        //TODO: in progress
        Class c = this.getClass();
        Field[] fields = c.getDeclaredFields();
        ArrayList<String> result = new ArrayList<>();
        for(Field field: fields)
            result.add(field.getName());
        return result;
    }


}
