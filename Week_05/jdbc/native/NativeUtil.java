import java.sql.*;

public class NativeUtil {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:6657/test2?user=root&password=123456";

    private static Connection conn = null;

    static {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
