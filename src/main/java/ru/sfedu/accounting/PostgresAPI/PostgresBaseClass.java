package ru.sfedu.accounting.PostgresAPI;

public class PostgresBaseClass {
    protected String relation;
    protected final String RELATION_PLACE_HOLDER = "RELATION";
    protected final String ATTRIBUTES_PLACE_HOLDER = "...";
    protected final String WHERE_CONDITIONAL = "where_condition";
    public PostgresBaseClass(String relation){
        this.relation = relation;
    }
}
