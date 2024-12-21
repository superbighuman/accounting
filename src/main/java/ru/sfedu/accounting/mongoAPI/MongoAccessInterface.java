package ru.sfedu.accounting.mongoAPI;

import com.mongodb.client.MongoClient;

public interface MongoAccessInterface {
    public String getURL();
    public String getHost();
    public String getUser();
    public String getPass();
    public MongoClient getClient();
}
