package sgpp.controlador;

import java.sql.SQLException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import sgpp.modelo.dao.AutoevaluacionDAO;
import sgpp.modelo.dao.EstudianteDAO;
import sgpp.modelo.pojo.Estudiante;
import sgpp.utilidad.CargadorVista;
import sgpp.utilidad.SesionActiva;
import sgpp.utilidad.Utilidad;

public class MenuEstudianteController {

    @FXML
    private void clicHacerAutoevaluacion() {
        try {
            String nombre = SesionActiva.getUsuarioActual() != null
                    ? SesionActiva.getUsuarioActual().getNombreCompleto() : "";
            Estudiante estudiante = EstudianteDAO.obtenerPorNombre(nombre);

            if (estudiante != null
                    && AutoevaluacionDAO.existe(estudiante.getMatricula())) {
                Utilidad.mostrarAdvertencia("Autoevaluacion ya registrada",
                        "El expediente ya cuenta con una autoevaluacion. "
                        + "No es posible realizarla de nuevo.");
                return;
            }
            CargadorVista.cambiarVista("FXML_AutoevaluacionEstudiante.fxml");
        } catch (SQLException excepcion) {
            Utilidad.mostrarError("Error de conexion",
                    "No hay conexion con la base de datos.\n" + excepcion.getMessage());
        }
    }

    @FXML
    private void clicCerrarSesion() {
        SesionActiva.cerrarSesion();
        CargadorVista.cambiarVista("FXML_InicioSesion.fxml");
    }

    @FXML
    private void clicSalir() {
        Platform.exit();
    }
}
