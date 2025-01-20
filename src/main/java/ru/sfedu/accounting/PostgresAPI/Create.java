package ru.sfedu.accounting.PostgresAPI;

import ru.sfedu.accounting.Models.Model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class Create extends PostgresBaseClass implements ICreate{
    protected String createQuery = "CREATE TABLE RELATION (...)";
    protected String insertQuery = "INSERT INTO RELATION VALUES (...)";
    public Create(String relation) {
        super(relation);
        insertQuery = insertQuery.replace(RELATION_PLACE_HOLDER, relation);
    }
    @Override
    public boolean createTable(Map<String, String> attributes) {
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        String executableString = String.format("create table if not exists %s (",relation);
        for(String attr: attributes.keySet()){
            String datatype = attributes.get(attr);
            executableString += attr + " " + datatype + ",";
        }
        executableString = executableString.substring(0,executableString.length()-1);
        executableString += ")";
        try {
            logger.info(executableString);
            statement.execute(executableString);
            psqlConn.closeConnection();
            return true;
        }
        catch (SQLException e){
            logger.info(e);
            psqlConn.closeConnection();
            return false;
        }
    }
    public static boolean initialisation(){
        ArrayList<Boolean> results = new ArrayList<>();
        boolean result;
        result = createUserTable();
        results.add(result);
        result = createUpdatingTrigger();
        results.add(result);
        return results.stream().reduce(true,(start, cur) -> (start && cur));
    }

    @Override
    public boolean addKey(String tableFrom, String attr, String foreignKey) {
        return false;
    }
    protected static boolean createUpdatingTrigger(){
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        String query = """
                   CREATE OR REPLACE FUNCTION update_updated_column()
                     RETURNS TRIGGER AS $$
                     BEGIN
                         NEW.updated = CURRENT_TIMESTAMP;
                         RETURN NEW;
                     END;
                     $$ LANGUAGE plpgsql;
                
                     CREATE TRIGGER update_users_updated
                     BEFORE UPDATE ON users
                     FOR EACH ROW
                     EXECUTE FUNCTION update_updated_column();
                """;
        try {
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            logger.info(e);
            return false;
        }
    }
    protected static boolean createUserTable(){
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        String query = """
                   CREATE TABLE IF NOT EXISTS users (
                     INN VARCHAR(12) PRIMARY KEY,
                     name VARCHAR(50),
                     surname VARCHAR(50),
                     workingPlace VARCHAR(50),
                     created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                     updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                   );
                
                """;
        try {
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            logger.info(e);
            return false;
        }
    }
    public boolean insertRecord(Model model){
        ArrayList<String> values = model.getFieldsValues();
        PSQLConn conn = new PSQLConn();
        Statement statement = conn.getStatement();
        String newInsertQuery = insertQuery;
        newInsertQuery = newInsertQuery.replace(ATTRIBUTES_PLACE_HOLDER, String.join(", ", values));
        try {
            statement.executeQuery(newInsertQuery);
            return true;
        }
        catch (Exception e){
            logger.info(e);
            return false;
        }
    }


}
