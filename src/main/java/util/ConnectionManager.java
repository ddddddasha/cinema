package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    public static final String URL_KEY = "db.url"; //не выносить константы
    public static final String USERNAME_KEY = "db.username";
    public static final String PASSWORD_KEY = "db.password";

    static {
        loadConnection();
    }
    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");//вынести
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ошибка загрузки драйвера");
        }
    }

    private ConnectionManager() {

    }
}
