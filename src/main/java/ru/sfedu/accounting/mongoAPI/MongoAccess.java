package ru.sfedu.accounting.mongoAPI;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;
import org.bson.Document;
import ru.sfedu.accounting.Constants.MongoConstants;
import ru.sfedu.accounting.Constants.ResourcesConstants;
import ru.sfedu.accounting.YAMLReader;

import java.util.Map;

public class MongoAccess implements MongoAccessInterface{
    private YAMLReader yamlReader;
    private Map<String, Object> content;
    private static MongoClient connection;
    private static Logger logger = Logger.getLogger(MongoAccess.class);
    public MongoAccess(){
        this.yamlReader = new YAMLReader(ResourcesConstants.API_KEYS);
        this.content = yamlReader.getContent();
    }
    @Override
    public String getURL() {
        return getHost();
    }

    @Override
    public String getHost() {
        String host = (String)content.get("mongodb_host");
        logger.info(host);
        return host;
    }

    @Override
    public String getUser() {
        String user = (String)content.get("mongodb_host");
        logger.info(user);
        return user;
    }
    public String getPass(){
        String pass = (String)content.get("mongodb_host");
        return pass;
    }
    public MongoClient getClient() throws MongoException{
        MongoClient connection;
        if (MongoAccess.connection==null) {
            try {
                connection = MongoClients.create(this.getURL());
                MongoAccess.connection = connection;
                return MongoAccess.connection;
            } catch (MongoException e) {
                logger.info(e);
                throw e;
            }
        }
        else{
            return MongoAccess.connection;
        }
    }
    public MongoDatabase getDatabase(){
        MongoClient connection = this.getClient();
        MongoDatabase mongoDatabase = connection.getDatabase(MongoConstants.MONGO_DB);
        return mongoDatabase;
    }
    public MongoCollection<Document> getCollection(){
        MongoDatabase mongoDatabase = this.getDatabase();
        return mongoDatabase.getCollection("HistoryContent");
    }
    public boolean close(){
        if(MongoAccess.connection != null) {
            MongoAccess.connection.close();
            MongoAccess.connection = null;
        }
        return true;
    }


}
