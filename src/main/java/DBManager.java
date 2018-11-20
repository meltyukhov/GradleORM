import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class DBManager {
    private Connection connection;

    DBManager(String properties) {
        connection = Connector.getConnection(properties);
    }

    void create(String query) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch(SQLException exc) {
            exc.printStackTrace();
        }
    }

    void insert(String query) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException exc) {
            exc.printStackTrace();
        }
    }
}
