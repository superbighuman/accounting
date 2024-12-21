package ru.sfedu.accounting.mongoAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import org.apache.log4j.Logger;
import org.bson.Document;
import ru.sfedu.accounting.Constants.MongoConstants;
import ru.sfedu.accounting.Constants.MongoStatus;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HistoryContent<T> {
    UUID uuid;
    String className;
    Date createdDate;
    String actor = MongoConstants.SYSTEM;
    String methodName;
    Map<String, Object> objectState;
    MongoStatus status;
    T object;
    private static Logger logger = Logger.getLogger(HistoryContent.class);
    public HistoryContent(String className, String methodName, T object){
        this.createdDate = new Date();
        this.className = className;
        this.methodName = methodName;
        this.object = object;
        this.objectState = getMap();
        this.uuid = UUID.randomUUID();
    }
    public HistoryContent(String className, String methodName, String actor, T object){
        this(className, methodName, object);
        this.actor = actor;
    }

    public UUID getUUID() {
        return uuid;
    }
    public String getClassName(){
        return className;
    }
    public String getMethodName(){
        return methodName;
    }
    public String getActor(){
        return actor;
    }
    public void saveDocument(){
        MongoAccess mongoAccess = new MongoAccess();
        MongoCollection<Document> collection = mongoAccess.getCollection();
        Gson gson = new Gson();
        Document document = Document.parse(gson.toJson(this));
        collection.insertOne(document);
        logger.info(this);
        mongoAccess.close();
    }
    public String toJSON(){
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(this);
            return json;
        } catch (JsonProcessingException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }
    private Map<String, Object> getMap(){
        Class c = object.getClass();
        Field[] fields = c.getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for(Field f: fields)
        {
            String attributeName = f.getName();
            String getterMethodName = "get"
                    + attributeName.substring(0, 1).toUpperCase()
                    + attributeName.substring(1, attributeName.length());
            Method m = null;
            try {
                m = c.getMethod(getterMethodName);
                Object valObject = m.invoke(object);
                map.put(attributeName, valObject);
            } catch (Exception e) {
                logger.info(e);
            }
        }
        return map;
    }
}
