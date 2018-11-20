import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class Connector {
    private static String _CONNECTION = "jdbc:mysql://localhost:3306/new_db?serverTimezone=UTC";

    static Connection getConnection(String properties) {

        Connection connection = null;
        _CONNECTION = getProperties(_CONNECTION, properties);
        System.out.println(_CONNECTION);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(_CONNECTION);
            System.out.println("Connected");
        }
        catch (SQLException | ClassNotFoundException exc) {
            exc.printStackTrace();
        }
        return connection;
    }

    private static String getProperties(String str, String propertiesFile) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(propertiesFile)));
            str += "&user=" + properties.get("user") + "&password=" + properties.get("password") + "&allowMultiQueries=true";
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
        return str;
    }
}
