import org.junit.Test;
import ru.sfedu.accounting.Models.User;

public class ModelTest {
    @Test
    public void userTest(){
        String inn = "123456789012";
        String name = "john";
        String surname = "jackson";
        String worikingPLace = "otz123";
        User john = new User(inn,name,surname,worikingPLace);
        System.out.println(john.getFields());
        boolean insert_result = john.insertRecord();
        System.out.println(insert_result);
    }
}
