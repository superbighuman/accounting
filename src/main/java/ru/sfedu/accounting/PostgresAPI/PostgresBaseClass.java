package ru.sfedu.accounting.PostgresAPI;

public class PostgresBaseClass {
    protected String relation;
    protected final String RELATION_PLACE_HOLDER = "RELATION";
    protected final String ATTRIBUTES_PLACE_HOLDER = "...";
    public PostgresBaseClass(String relation){
        this.relation = relation;
    }
}
