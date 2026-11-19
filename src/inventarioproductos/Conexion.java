package inventarioproductos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

    private static final Properties props = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("No se encontró config.properties: " + e.getMessage());
        }
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("usuario"),
                props.getProperty("clave"));
    }
}