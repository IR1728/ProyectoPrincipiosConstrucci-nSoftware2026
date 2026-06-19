package sgpp.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sgpp.modelo.ConexionBD;
import sgpp.modelo.pojo.OrganizacionVinculada;

public class OrganizacionVinculadaDAO {

    public static List<OrganizacionVinculada> obtenerTodas() throws SQLException {
        List<OrganizacionVinculada> organizaciones = new ArrayList<>();
        String consulta = "SELECT * FROM organizacion_vinculada ORDER BY nombre";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                organizaciones.add(mapear(resultado));
            }
        }
        return organizaciones;
    }

    public static OrganizacionVinculada obtenerPorIdentificador(int identificador)
            throws SQLException {
        String consulta = "SELECT * FROM organizacion_vinculada "
                + "WHERE id_organizacion_vinculada = ?";
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

    private static OrganizacionVinculada mapear(ResultSet resultado) throws SQLException {
        return new OrganizacionVinculada(
                resultado.getInt("id_organizacion_vinculada"),
                resultado.getString("nombre"),
                resultado.getString("sector"),
                resultado.getString("correo"),
                resultado.getString("telefono"),
                resultado.getString("direccion"),
                resultado.getString("ciudad"),
                resultado.getString("estado"));
    }
}
