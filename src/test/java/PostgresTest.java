import org.junit.Test;
import ru.sfedu.accounting.Constants.ResourcesConstants;
import ru.sfedu.accounting.YAMLReader;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PostgresTest {
    @Test
    public void testConnection() throws SQLException {

        Map<String, Object> keys = new YAMLReader(ResourcesConstants.API_KEYS).getContent();
        String url = (String) keys.get(ResourcesConstants.POSTGRES_HOST);
        String user = (String) keys.get(ResourcesConstants.POSTGRES_USER);
        String password = (String) keys.get(ResourcesConstants.POSTGRES_PASSWORD);
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        Connection conn = DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from test");
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();
    }
}
