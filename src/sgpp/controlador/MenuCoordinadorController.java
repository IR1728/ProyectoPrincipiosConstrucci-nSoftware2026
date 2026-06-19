package sgpp.controlador;

import javafx.application.Platform;
import javafx.fxml.FXML;
import sgpp.utilidad.CargadorVista;
import sgpp.utilidad.SesionActiva;

public class MenuCoordinadorController {

    @FXML
    private void clicRegistrarProyecto() {
        CargadorVista.cambiarVista("FXML_RegistrarProyecto.fxml");
    }

    @FXML
    private void clicAsignarProyecto() {
        CargadorVista.cambiarVista("FXML_AsignarProyecto.fxml");
    }

    @FXML
    private void clicProgramarEntregas() {
        CargadorVista.cambiarVista("FXML_ProgramarPracticas.fxml");
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
