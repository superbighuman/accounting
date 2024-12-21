package ru.sfedu.accounting.mongoAPI;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Map;

public interface MongoCreateInterface {
    public Document createDocument(Map<String, String> documentItems);
    public MongoCollection<Document> createCollection(String collection);


}
