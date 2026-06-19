package sgpp.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sgpp.modelo.ConexionBD;
import sgpp.modelo.pojo.EstadoReporte;
import sgpp.modelo.pojo.ReporteMensual;

public class ReporteMensualDAO {

    public static List<ReporteMensual> obtenerPendientes() throws SQLException {
        List<ReporteMensual> reportes = new ArrayList<>();
        String consulta = "SELECT * FROM reporte_mensual WHERE estado = 'PENDIENTE' "
                + "ORDER BY fecha_entrega ASC";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                Date fechaEntrega = resultado.getDate("fecha_entrega");
                ReporteMensual reporte = new ReporteMensual(
                        resultado.getInt("id_reporte_mensual"),
                        resultado.getInt("numero_reporte"),
                        resultado.getString("mes"),
                        fechaEntrega != null ? fechaEntrega.toLocalDate() : null,
                        resultado.getInt("horas_reportadas"),
                        EstadoReporte.valueOf(resultado.getString("estado")),
                        resultado.getString("nombre_estudiante"),
                        resultado.getString("contenido"));
                reporte.setObservaciones(resultado.getString("observaciones"));
                reportes.add(reporte);
            }
        }
        return reportes;
    }

    public static void validar(ReporteMensual reporte, String observaciones)
            throws SQLException {
        actualizarEstado(reporte.getIdentificador(), EstadoReporte.ACEPTADO, observaciones);
        reporte.setEstado(EstadoReporte.ACEPTADO);
        reporte.setObservaciones(observaciones);
    }

    public static void rechazar(ReporteMensual reporte, String observaciones)
            throws SQLException {
        actualizarEstado(reporte.getIdentificador(), EstadoReporte.RECHAZADO, observaciones);
        reporte.setEstado(EstadoReporte.RECHAZADO);
        reporte.setObservaciones(observaciones);
    }

    private static void actualizarEstado(int identificador, EstadoReporte estado,
            String observaciones) throws SQLException {
        String actualizacion = "UPDATE reporte_mensual SET estado = ?, observaciones = ? "
                + "WHERE id_reporte_mensual = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(actualizacion)) {
            sentencia.setString(1, estado.name());
            sentencia.setString(2, observaciones);
            sentencia.setInt(3, identificador);
            sentencia.executeUpdate();
        }
    }
}
