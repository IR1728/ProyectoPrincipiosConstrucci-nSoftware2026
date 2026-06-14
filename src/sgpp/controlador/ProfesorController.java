package sgpp.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import sgpp.modelo.ReporteMensual;
import sgpp.servicio.ContextoAplicacion;
import sgpp.servicio.ReporteServicio;
import sgpp.servicio.SesionServicio;

public class ProfesorController extends ControladorBase implements Initializable {

    @FXML private Label lblSesion;
    @FXML private Label lblMensaje;
    @FXML private ListView<ReporteMensual> lstReportesPendientes;
    @FXML private TextArea txtObservacionesReporte;

    private final SesionServicio sesionServicio = ContextoAplicacion.getSesionServicio();
    private final ReporteServicio reporteServicio = ContextoAplicacion.getReporteServicio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblSesion.setText(sesionServicio.getUsuarioActual().getNombreUsuario() + " - Profesor");
        cargarReportes();
    }

    @FXML
    private void aceptarReporte() {
        validarReporte(true);
    }

    @FXML
    private void rechazarReporte() {
        validarReporte(false);
    }

    @FXML
    private void cerrarSesion() {
        sesionServicio.cerrarSesion();
        cambiarVista(lblMensaje, "/sgpp/vista/FXMLLogin.fxml");
    }

    private void validarReporte(boolean aceptar) {
        try {
            ReporteMensual reporte = lstReportesPendientes.getSelectionModel().getSelectedItem();
            String accion = aceptar ? "aceptar" : "rechazar";
            if (confirmar("Confirmar validacion", "Desea " + accion + " el reporte seleccionado?")) {
                if (aceptar) {
                    reporteServicio.aceptar(reporte);
                    lblMensaje.setText("CU18 completado. Reporte aceptado.");
                } else {
                    reporteServicio.rechazar(reporte);
                    lblMensaje.setText("CU18 completado. Reporte rechazado.");
                }
                txtObservacionesReporte.clear();
                cargarReportes();
            }
        } catch (RuntimeException ex) {
            lblMensaje.setText(ex.getMessage());
            mostrarError(ex.getMessage());
        }
    }

    private void cargarReportes() {
        lstReportesPendientes.setItems(FXCollections.observableArrayList(reporteServicio.listarReportesPendientes()));
    }
}
