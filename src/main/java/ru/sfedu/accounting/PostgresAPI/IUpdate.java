package ru.sfedu.accounting.PostgresAPI;

import org.apache.log4j.Logger;

public interface IUpdate {
    Logger logger = Logger.getLogger(IUpdate.class);
    public boolean updateRecord(String key, String attr, Object newValue);
}
