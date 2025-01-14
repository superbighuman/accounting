import org.junit.Test;
import ru.sfedu.accounting.Models.User;

public class ModelTest {
    @Test
    public void userInsert(){
        String inn = "123456789012";
        String name = "john";
        String surname = "jackson";
        String worikingPLace = "otz123";
        User john = new User(inn,name,surname,worikingPLace);
        System.out.println(john.getFieldsValues());
        boolean insert_result = john.insertRecord();
        System.out.println(insert_result);
    }
    @Test
    public void userExistPositive(){
        String inn = "123456789012";
        String name = "john";
        String surname = "jackson";
        String worikingPLace = "otz123";
        User john = new User(inn,name,surname,worikingPLace);
        assert john.exists();
        System.out.println("john exist");
    }
    @Test
    public void userExistNegative(){
        String inn = "111111111111";
        String name = "john";
        String surname = "jackson";
        String worikingPLace = "otz123";
        User bob = new User(inn,name,surname,worikingPLace);
        System.out.println(bob.exists());
        assert bob.exists();
        System.out.println("bob exist");
    }
}
