package sgpp.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import sgpp.modelo.ConexionBD;
import sgpp.modelo.pojo.Proyecto;

public class ProyectoDAO {

    public static void registrar(Proyecto proyecto) throws SQLException {
        String insercion = "INSERT INTO proyecto (nombre, objetivo_general, metodologia, "
                + "numero_maximo_participantes, fecha_inicio, fecha_finalizacion, "
                + "id_organizacion_vinculada, id_responsable_tecnico, lugares_disponibles) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(
                        insercion, Statement.RETURN_GENERATED_KEYS)) {
            sentencia.setString(1, proyecto.getNombre());
            sentencia.setString(2, proyecto.getObjetivoGeneral());
            sentencia.setString(3, proyecto.getMetodologia());
            sentencia.setInt(4, proyecto.getNumeroMaximoParticipantes());
            establecerFecha(sentencia, 5, proyecto.getFechaInicio());
            establecerFecha(sentencia, 6, proyecto.getFechaFinalizacion());
            sentencia.setInt(7, proyecto.getIdentificadorOrganizacionVinculada());
            sentencia.setInt(8, proyecto.getIdentificadorResponsableTecnico());
            sentencia.setInt(9, proyecto.getLugaresDisponibles());
            sentencia.executeUpdate();
            try (ResultSet llaves = sentencia.getGeneratedKeys()) {
                if (llaves.next()) {
                    proyecto.setIdentificador(llaves.getInt(1));
                }
            }
        }
    }

    public static List<Proyecto> obtenerConLugaresDisponibles() throws SQLException {
        List<Proyecto> proyectos = new ArrayList<>();
        String consulta = "SELECT * FROM proyecto WHERE lugares_disponibles >= 1 ORDER BY nombre";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                proyectos.add(mapear(resultado));
            }
        }
        return proyectos;
    }

    public static Proyecto obtenerPorIdentificador(int identificador) throws SQLException {
        String consulta = "SELECT * FROM proyecto WHERE id_proyecto = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
            sentencia.setInt(1, identificador);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return mapear(resultado);
                }
            }
        }
        return null;
    }

    private static Proyecto mapear(ResultSet resultado) throws SQLException {
        Date fechaInicio = resultado.getDate("fecha_inicio");
        Date fechaFinalizacion = resultado.getDate("fecha_finalizacion");
        return new Proyecto(
                resultado.getInt("id_proyecto"),
                resultado.getString("nombre"),
                resultado.getString("objetivo_general"),
                resultado.getString("metodologia"),
                resultado.getInt("numero_maximo_participantes"),
                fechaInicio != null ? fechaInicio.toLocalDate() : null,
                fechaFinalizacion != null ? fechaFinalizacion.toLocalDate() : null,
                resultado.getInt("id_organizacion_vinculada"),
                resultado.getInt("id_responsable_tecnico"),
                resultado.getInt("lugares_disponibles"));
    }

    private static void establecerFecha(PreparedStatement sentencia, int indice,
            LocalDate fecha) throws SQLException {
        if (fecha == null) {
            sentencia.setNull(indice, Types.DATE);
        } else {
            sentencia.setDate(indice, Date.valueOf(fecha));
        }
    }
}
