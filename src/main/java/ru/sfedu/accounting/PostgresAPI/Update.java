package ru.sfedu.accounting.PostgresAPI;

public class Update extends PostgresBaseClass implements IUpdate{
    public Update(String relation) {
        super(relation);
    }

    @Override
    public boolean updateRecord(String key, String attr, Object newValue) {
        return false;
    }
}
