import org.junit.Test;
import ru.sfedu.accounting.Models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
    @Test
    public void modelFields(){
        String inn = "111111111111";
        String name = "john";
        String surname = "jackson";
        String worikingPLace = "otz123";
        User bob = new User(inn,name,surname,worikingPLace);
        System.out.println(bob.getFields());
        System.out.println(bob.getFieldsValues());
        System.out.println(bob.getItems());
    }
    @Test
    public void StringTest(){
        String a = "a";
        String b = "b";
        String c = "c";
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(a);
        arrayList.add(b);
        arrayList.add(c);
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put(a,a);
        map.put(b,b);
        map.put(c,c);
        System.out.println(arrayList);
        System.out.println(map);
    }
    @Test
    public void updateTest(){
        String inn = "123456789012";
        String name = "john";
        String surname = "jackson";
        String worikingPLace = "otz123";
        User john = new User(inn,name,surname,worikingPLace);
        System.out.println("john exist");
        john.setName("Sam");
        System.out.println(john);
        boolean result = john.updateRecord();
        System.out.println(result);
    }
    @Test
    public void deleteTest(){
        String inn = "11111";
        String name = "john";
        String surname = "jackson";
        String worikingPLace = "otz123";
        User john = new User(inn,name,surname,worikingPLace);
        john.insertRecord();
        assert john.exists(): "Не вставилось";
        john.deleteRecord();
        assert !john.exists(): "Не Удалилось";
        System.out.println("Удалилось");
    }

}
