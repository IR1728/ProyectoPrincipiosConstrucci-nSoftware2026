package sgpp.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sgpp.modelo.ConexionBD;
import sgpp.modelo.pojo.ResponsableTecnico;

public class ResponsableTecnicoDAO {

    public static List<ResponsableTecnico> obtenerPorOrganizacion(
            int identificadorOrganizacion) throws SQLException {
        List<ResponsableTecnico> responsables = new ArrayList<>();
        String consulta = "SELECT * FROM responsable_tecnico "
                + "WHERE id_organizacion_vinculada = ? ORDER BY nombre";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
            sentencia.setInt(1, identificadorOrganizacion);
            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    responsables.add(new ResponsableTecnico(
                            resultado.getInt("id_responsable_tecnico"),
                            resultado.getString("nombre"),
                            resultado.getString("departamento"),
                            resultado.getString("puesto"),
                            resultado.getString("correo"),
                            resultado.getInt("id_organizacion_vinculada")));
                }
            }
        }
        return responsables;
    }
}
