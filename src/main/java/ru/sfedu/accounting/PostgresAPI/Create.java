package ru.sfedu.accounting.PostgresAPI;

import java.util.Map;

public class Create implements ICreate{
    @Override
    public boolean createTable(String name, Map<String, String> attributes) {
        return false;
    }
}
