package ru.sfedu.accounting.PostgresAPI;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class Create extends PostgresBaseClass implements ICreate{
    public Create(String relation) {
        super(relation);
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
    public boolean initialisation(){
        return true;
    }

    @Override
    public boolean addKey(String tableFrom, String attr, String foreignKey) {
        return false;
    }
    public boolean createUpdatingTrigger(){
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
    public boolean createUserTable(){
        PSQLConn psqlConn = new PSQLConn();
        Statement statement = psqlConn.getStatement();
        String query = """
                   CREATE TABLE IF NOT EXISTS users (
                     INN VARCHAR(12) PRIMARY KEY,
                     name VARCHAR(50),
                     surname VARCHAR(50),
                     working_place VARCHAR(50),
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


}
