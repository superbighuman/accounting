package ru.sfedu.accounting.PostgresAPI;

import java.util.Map;

public interface ICreate {
    public boolean createTable(String name, Map<String, String> attributes);

}
