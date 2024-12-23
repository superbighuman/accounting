import org.junit.Test;
import ru.sfedu.accounting.FileDB.CSVCreate;
import ru.sfedu.accounting.FileDB.CSVDelete;
import ru.sfedu.accounting.FileDB.CSVRead;
import ru.sfedu.accounting.FileDB.CSVWrite;

import java.util.ArrayList;

public class DataProviderTest {
    @Test
    public void csvCreate(){
        String name = "table";
        String[] attrs = {"id", "name", "surname"};
        CSVCreate csvCreate = new CSVCreate();
        boolean result = csvCreate.createTable(name,attrs);
        System.out.println(result);
    }
    @Test
    public void csvReadAttrs(){
        String table = "table";
        ArrayList<String> attrs = new CSVRead().readAttrs(table);
        System.out.println(attrs);
    }
    @Test
    public void csvInsert(){
        String table = "table";
        String[] man = {"1", "John", "Johnson"};
        CSVWrite csvWrite = new CSVWrite();
        boolean result = csvWrite.insertRecord(table, man);
        System.out.println(result);
    }
    @Test
    public void csvDelete(){
        String table = "table";
        String id = "2";
        CSVDelete csvDelete = new CSVDelete();
        csvDelete.deleteRecord(table, id);
    }
}
