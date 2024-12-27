package ru.sfedu.accounting.PostgresAPI;

import org.apache.log4j.Logger;

import java.util.Map;

public interface ICreate {
    Logger logger = Logger.getLogger(ICreate.class);
    public boolean createTable(String name, Map<String, String> attributes);
    public boolean initialisation();
    public boolean addKey(String tableFrom, String tableTo, String attr, String foreignKey);

}
