package ru.sfedu.accounting.mongoAPI;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;

public class MongoRead {
    public ArrayList<Document> getDocuments(){
        MongoAccess mongoAccess = new MongoAccess();
        MongoCollection<Document> collection = mongoAccess.getCollection();
        FindIterable<Document> documents = collection.find();
        ArrayList<Document> result = new ArrayList<>();
        for(Document document:documents){
            result.add(document);
        }
        return result;
    }
}
