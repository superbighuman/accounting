package ru.sfedu.accounting.PostgresAPI;

import org.apache.log4j.Logger;
import ru.sfedu.accounting.Models.Model;

public interface IDelete {
    Logger logger = Logger.getLogger(IDelete.class);
    public boolean dropTable();
    public boolean truncateTable();
    public boolean deleteRecord(Model model);
}
