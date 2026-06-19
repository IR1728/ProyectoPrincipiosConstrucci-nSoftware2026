package sgpp.controlador;

import javafx.application.Platform;
import javafx.fxml.FXML;
import sgpp.utilidad.CargadorVista;
import sgpp.utilidad.SesionActiva;

public class MenuProfesorController {

    @FXML
    private void clicValidarReportes() {
        CargadorVista.cambiarVista("FXML_ValidarReporte.fxml");
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
