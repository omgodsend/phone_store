import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bookstoredb";
    private static final String DB_USER = "root";

    private static final String DB_PASSWORD = "";

    public static Connection getConnection(){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to db successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
