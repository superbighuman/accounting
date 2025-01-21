package ru.sfedu.accounting.PostgresAPI;

import ru.sfedu.accounting.Models.Model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class Update extends PostgresBaseClass implements IUpdate{
    protected  String UPDATE_QUERY = "UPDATE RELATION SET VALUES WHERE ";
    protected  String NEW_VALUES_PLACEHOLDER = "VALUES";
    public Update(String relation) {
        super(relation);
        UPDATE_QUERY = UPDATE_QUERY.replace(RELATION_PLACE_HOLDER, relation);

    }

    @Override
    public boolean updateRecord(Model model) {
        String updateQuery = prepareUpdateString(model);
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        try {
            statement.execute(updateQuery);
            return true;
        }
        catch (SQLException e){
            logger.info(e);
            return false;
        }
    }
    public boolean updateFullRecord(Model model){
        //TODO: in progress
        ArrayList<String> attributes = model.getFieldsValues();
        return true;
    }
    protected String prepareUpdateString(Model model){
        Map<String, String> map= model.getItems();
        String key = model.keyGet();
        String keyValue = map.get(key);
        map.remove(key);
        String updateQuery = UPDATE_QUERY + key + "=" + keyValue;
        String newValues = "";
        for(String attr: map.keySet()){
            newValues+= attr + " = " + map.get(attr) + ",";
        }
        newValues = newValues.substring(0,newValues.length()-1);
        updateQuery = updateQuery.replace(NEW_VALUES_PLACEHOLDER, newValues);
        return updateQuery;
    }
}
