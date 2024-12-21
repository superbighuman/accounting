import org.apache.log4j.Logger;
import org.bson.Document;
import org.junit.Test;
import ru.sfedu.accounting.mongoAPI.MongoAccess;
import ru.sfedu.accounting.mongoAPI.MongoRead;

import java.util.ArrayList;

public class MongoAccessTest {
    @Test
    public void accessTest(){
        Logger logger = Logger.getLogger(getClass());
        MongoAccess mongoAccess = new MongoAccess();
        logger.info(mongoAccess);
        String host = mongoAccess.getHost();
        logger.info(host);
        String user = mongoAccess.getUser();
        logger.info(user);
        String password = mongoAccess.getPass();
        logger.info(password);
        String url = mongoAccess.getURL();
        logger.info(url);

    }
    @Test
    public void HistoryTest(){
        Example human = new Example("Bob", "Bobson");
        human.setName("Ivan");
        human.setSurname("Johnson");
        MongoRead reader = new MongoRead();
        ArrayList<Document> documents =  reader.getDocuments();
        System.out.println(documents);
    }

}
