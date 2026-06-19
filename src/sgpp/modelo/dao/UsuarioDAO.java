package sgpp.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sgpp.modelo.ConexionBD;
import sgpp.modelo.pojo.TipoUsuario;
import sgpp.modelo.pojo.Usuario;

public class UsuarioDAO {

    public static Usuario autenticar(String numeroDePersonal, String contrasena)
            throws SQLException {
        String consulta = "SELECT numero_personal, contrasena, nombre_completo, tipo_usuario "
                + "FROM usuario WHERE numero_personal = ? AND contrasena = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
            sentencia.setString(1, numeroDePersonal);
            sentencia.setString(2, contrasena);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return new Usuario(
                            resultado.getString("numero_personal"),
                            resultado.getString("contrasena"),
                            resultado.getString("nombre_completo"),
                            TipoUsuario.valueOf(resultado.getString("tipo_usuario")));
                }
            }
        }
        return null;
    }
}
