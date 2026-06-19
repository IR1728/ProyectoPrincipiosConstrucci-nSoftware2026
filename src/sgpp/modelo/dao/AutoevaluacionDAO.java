package sgpp.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringJoiner;
import sgpp.modelo.ConexionBD;
import sgpp.modelo.pojo.Autoevaluacion;

public class AutoevaluacionDAO {

    public static boolean existe(String matriculaEstudiante) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM autoevaluacion WHERE matricula_estudiante = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
            sentencia.setString(1, matriculaEstudiante);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public static void registrar(Autoevaluacion autoevaluacion) throws SQLException {
        String insercion = "INSERT INTO autoevaluacion (matricula_estudiante, "
                + "calificaciones, comentario) VALUES (?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(insercion)) {
            sentencia.setString(1, autoevaluacion.getMatriculaEstudiante());
            sentencia.setString(2, unirCalificaciones(autoevaluacion));
            sentencia.setString(3, autoevaluacion.getComentario());
            sentencia.executeUpdate();
        }
    }

    private static String unirCalificaciones(Autoevaluacion autoevaluacion) {
        StringJoiner cadena = new StringJoiner(",");
        for (Integer calificacion : autoevaluacion.getCalificaciones()) {
            cadena.add(String.valueOf(calificacion));
        }
        return cadena.toString();
    }
}
