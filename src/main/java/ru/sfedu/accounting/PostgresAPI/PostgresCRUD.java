package ru.sfedu.accounting.PostgresAPI;

public class PostgresCRUD {
    protected Create postgresCreate;
    protected Update postgresUpdate;
    protected Read postgresRead;
    protected Delete postgresDelete;
    PostgresCRUD(String relation){
        postgresDelete = new Delete(relation);
        postgresCreate = new Create(relation);
        postgresRead = new Read(relation);
        postgresUpdate = new Update(relation);
    }
}
