package util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {//����������� �� ������ HashTable(Map)

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String get (String key){
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties(){
        try (InputStream stream = PropertiesUtil.class.getClassLoader().
                getResourceAsStream("application.properties")) { //��������� ���� � stream
            PROPERTIES.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertiesUtil() {
    }
}
