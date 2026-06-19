package sgpp.modelo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static final String ARCHIVO_CONFIGURACION = "/configuracion-bd.properties";

    public static Connection obtenerConexion() throws SQLException {
        Properties configuracion = cargarConfiguracion();

        String driver = configuracion.getProperty("bd.driver");
        String ip = configuracion.getProperty("bd.ip");
        String puerto = configuracion.getProperty("bd.puerto");
        String nombreBaseDeDatos = configuracion.getProperty("bd.nombre");
        String usuario = configuracion.getProperty("bd.usuario");
        String password = configuracion.getProperty("bd.password");

        String urlConexion = "jdbc:mysql://" + ip + ":" + puerto + "/" + nombreBaseDeDatos
                + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";

        try {
            Class.forName(driver);
            return DriverManager.getConnection(urlConexion, usuario, password);
        } catch (ClassNotFoundException excepcion) {
            throw new SQLException("No se encontro el driver de MySQL: "
                    + excepcion.getMessage(), excepcion);
        }
    }

    private static Properties cargarConfiguracion() throws SQLException {
        Properties configuracion = new Properties();
        try (InputStream entrada =
                ConexionBD.class.getResourceAsStream(ARCHIVO_CONFIGURACION)) {
            if (entrada == null) {
                throw new SQLException("No se encontro el archivo de configuracion: "
                        + ARCHIVO_CONFIGURACION);
            }
            configuracion.load(entrada);
        } catch (IOException excepcion) {
            throw new SQLException("No fue posible leer la configuracion de la base de datos: "
                    + excepcion.getMessage(), excepcion);
        }
        return configuracion;
    }
}
