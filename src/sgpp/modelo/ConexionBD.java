package sgpp.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String NOMBRE_BD = "practicas_sgpp";
    private static final String IP = "localhost";
    private static final String PUERTO = "3306";
    private static final String USUARIO_BD = "practicas_app";
    private static final String PASSWORD = "Practicas.2026";

    public static Connection obtenerConexion() throws SQLException {
        try {
            String URL_CONEXION = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + NOMBRE_BD
                    + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";

            Class.forName(DRIVER);

            return DriverManager.getConnection(URL_CONEXION, USUARIO_BD, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontro el driver de MySQL: " + e.getMessage(), e);
        }
    }

}
