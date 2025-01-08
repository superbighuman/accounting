package ru.sfedu.accounting.PostgresAPI;

import org.apache.log4j.Logger;
import ru.sfedu.accounting.Models.Model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostgresCRUD {
    protected Logger logger = Logger.getLogger(PostgresCRUD.class);
    protected Create postgresCreate;
    protected Update postgresUpdate;
    protected Read postgresRead;
    protected Delete postgresDelete;
    public PostgresCRUD(String relation){
        postgresDelete = new Delete(relation);
        postgresCreate = new Create(relation);
        postgresRead = new Read(relation);
        postgresUpdate = new Update(relation);
    }


}
