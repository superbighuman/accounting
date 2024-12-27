import org.junit.Test;
import ru.sfedu.accounting.PropertiesReader;
import ru.sfedu.accounting.XMLReader;
import ru.sfedu.accounting.YAMLReader;
import ru.sfedu.accounting.Constants.ResourcesConstants;
public class ReadersTest {
    @Test
    public void XMLTest(){
        XMLReader reader = new XMLReader(ResourcesConstants.XMLExample);
        System.out.println(reader.getContent("planet"));
    }

    @Test
    public void YAMLMapTest(){
        YAMLReader reader = new YAMLReader(ResourcesConstants.YAMLExample);
        System.out.println(reader.getContent());
    }
    @Test
    public void PropertiesTest(){
        PropertiesReader reader = new PropertiesReader(ResourcesConstants.PropertiesExample);
        System.out.println(reader.getContent());
    }
}
