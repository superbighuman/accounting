package ru.sfedu.accounting.PostgresAPI;

import org.apache.log4j.Logger;
import ru.sfedu.accounting.Models.Model;

public interface IUpdate {
    Logger logger = Logger.getLogger(IUpdate.class);
    public boolean updateRecord(Model model);
}
