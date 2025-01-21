import org.junit.Test;
import ru.sfedu.accounting.AccountingClient;
import org.apache.log4j.Logger;
public class AccountingClientTest {
//    public void main(String[] args){
//        logTest();
//    }
    @Test
    public void logTest(){
        AccountingClient client = new AccountingClient();
        this.logBasicSystemInfo();

    }
    @Test
    public void logBasicSystemInfo(){
        Logger logger= Logger.getLogger(AccountingClientTest.class);
        logger.info("Launching the application...");
        logger.info(
                "Operating System: " + System.getProperty("os.name") + " "
                        + System.getProperty("os.version")
        );
        logger.info("JRE: " + System.getProperty("java.version"));
        logger.info("Java Launched From: " + System.getProperty("java.home"));
        logger.info("Class Path: " + System.getProperty("java.class.path"));
        logger.info("Library Path: " + System.getProperty("java.library.path"));
        logger.info("User Home Directory: " + System.getProperty("user.home"));
        logger.info("User Working Directory: " + System.getProperty("user.dir"));
        logger.info("Test INFO logging.");
    }
}
