package sgpp.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sgpp.modelo.ConexionBD;
import sgpp.modelo.pojo.Estudiante;
import sgpp.modelo.pojo.Proyecto;

public class EstudianteDAO {

    public static List<Estudiante> obtenerSinProyecto() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();

        String consulta = "SELECT * FROM estudiante WHERE id_proyecto_asignado = 0 "
                + "ORDER BY semestre DESC, promedio DESC";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                estudiantes.add(mapear(resultado));
            }
        }
        return estudiantes;
    }

    public static Estudiante obtenerPorNombre(String nombre) throws SQLException {
        String consulta = "SELECT * FROM estudiante WHERE nombre = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
            sentencia.setString(1, nombre);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return mapear(resultado);
                }
            }
        }
        return null;
    }

    public static void asignarProyecto(Estudiante estudiante, Proyecto proyecto)
            throws SQLException {
        String actualizarEstudiante = "UPDATE estudiante SET id_proyecto_asignado = ? "
                + "WHERE matricula = ?";
        String descontarLugar = "UPDATE proyecto "
                + "SET lugares_disponibles = lugares_disponibles - 1 WHERE id_proyecto = ?";
        Connection conexion = null;
        try {
            conexion = ConexionBD.obtenerConexion();
            conexion.setAutoCommit(false);
            try (PreparedStatement sentenciaEstudiante =
                            conexion.prepareStatement(actualizarEstudiante);
                    PreparedStatement sentenciaProyecto =
                            conexion.prepareStatement(descontarLugar)) {
                sentenciaEstudiante.setInt(1, proyecto.getIdentificador());
                sentenciaEstudiante.setString(2, estudiante.getMatricula());
                sentenciaEstudiante.executeUpdate();

                sentenciaProyecto.setInt(1, proyecto.getIdentificador());
                sentenciaProyecto.executeUpdate();
            }
            conexion.commit();
            estudiante.setIdentificadorProyectoAsignado(proyecto.getIdentificador());
            proyecto.setLugaresDisponibles(proyecto.getLugaresDisponibles() - 1);
        } catch (SQLException excepcion) {
            if (conexion != null) {
                conexion.rollback();
            }
            throw excepcion;
        } finally {
            if (conexion != null) {
                conexion.setAutoCommit(true);
                conexion.close();
            }
        }
    }

    private static Estudiante mapear(ResultSet resultado) throws SQLException {
        return new Estudiante(
                resultado.getString("matricula"),
                resultado.getString("nombre"),
                resultado.getString("correo"),
                resultado.getString("telefono"),
                resultado.getInt("semestre"),
                resultado.getDouble("promedio"),
                resultado.getInt("id_proyecto_asignado"));
    }
}
