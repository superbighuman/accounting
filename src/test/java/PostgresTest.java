import org.junit.Test;
import java.sql.*;
import java.util.Properties;

public class PostgresTest {
    @Test
    public void testConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/fond";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "1965");
        Connection conn = DriverManager.getConnection(url, props);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from TEST");
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();


    }
}
