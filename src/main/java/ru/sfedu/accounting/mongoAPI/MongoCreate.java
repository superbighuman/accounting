package ru.sfedu.accounting.mongoAPI;

import com.mongodb.client.MongoCollection;
import org.apache.log4j.Logger;
import org.bson.Document;

import java.util.Map;

public class MongoCreate implements MongoCreateInterface{
    Logger logger = Logger.getLogger(MongoCreate.class);
    public Document createDocument(Map<String, String> documentItems){
        Document document = new Document(documentItems);
        logger.info(document);
        return document;
    }

    @Override
    public MongoCollection<Document> createCollection(String collection) {
        return null;
    }

}
