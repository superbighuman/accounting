package ru.sfedu.accounting.PostgresAPI;

import org.apache.log4j.Logger;

import java.util.Map;

public interface ICreate {
    Logger logger = Logger.getLogger(ICreate.class);
    public boolean createTable( Map<String, String> attributes);
    public boolean initialisation();
    public boolean addKey(String tableFrom, String attr, String foreignKey);

}
