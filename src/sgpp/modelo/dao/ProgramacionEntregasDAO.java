package sgpp.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import sgpp.modelo.ConexionBD;
import sgpp.modelo.pojo.ProgramacionEntregas;

public class ProgramacionEntregasDAO {

    public static void guardar(ProgramacionEntregas programacion) throws SQLException {
        String borrado = "DELETE FROM programacion_entregas";
        String insercion = "INSERT INTO programacion_entregas ("
                + "apertura_documento_inicial, limite_documento_inicial, "
                + "apertura_documento_parcial, limite_documento_parcial, "
                + "apertura_documento_final, limite_documento_final, "
                + "apertura_reporte_uno, limite_reporte_uno, "
                + "apertura_reporte_dos, limite_reporte_dos, "
                + "apertura_reporte_tres, limite_reporte_tres, "
                + "apertura_reporte_cuatro, limite_reporte_cuatro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conexion = null;
        try {
            conexion = ConexionBD.obtenerConexion();
            conexion.setAutoCommit(false);
            try (PreparedStatement sentenciaBorrado = conexion.prepareStatement(borrado);
                    PreparedStatement sentenciaInsercion =
                            conexion.prepareStatement(insercion)) {
                sentenciaBorrado.executeUpdate();

                LocalDate[] fechas = {
                    programacion.getAperturaDocumentoInicial(),
                    programacion.getLimiteDocumentoInicial(),
                    programacion.getAperturaDocumentoParcial(),
                    programacion.getLimiteDocumentoParcial(),
                    programacion.getAperturaDocumentoFinal(),
                    programacion.getLimiteDocumentoFinal(),
                    programacion.getAperturaReporteUno(),
                    programacion.getLimiteReporteUno(),
                    programacion.getAperturaReporteDos(),
                    programacion.getLimiteReporteDos(),
                    programacion.getAperturaReporteTres(),
                    programacion.getLimiteReporteTres(),
                    programacion.getAperturaReporteCuatro(),
                    programacion.getLimiteReporteCuatro()
                };
                for (int indice = 0; indice < fechas.length; indice++) {
                    establecerFecha(sentenciaInsercion, indice + 1, fechas[indice]);
                }
                sentenciaInsercion.executeUpdate();
            }
            conexion.commit();
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

    private static void establecerFecha(PreparedStatement sentencia, int indice,
            LocalDate fecha) throws SQLException {
        if (fecha == null) {
            sentencia.setNull(indice, Types.DATE);
        } else {
            sentencia.setDate(indice, Date.valueOf(fecha));
        }
    }
}
