package ru.sfedu.accounting.PostgresAPI;

import ru.sfedu.accounting.Models.Model;

import java.util.ArrayList;

public class Update extends PostgresBaseClass implements IUpdate{
    protected final String UPDATE_QUERY = "UPDATE ... WHERE ";
    public Update(String relation) {
        super(relation);
    }

    @Override
    public boolean updateRecord(Model model) {

        return false;
    }
    public boolean updateFullRecord(Model model){
        //TODO: in progress
        ArrayList<String> attributes = model.getFieldsValues();
        return true;
    }
}
