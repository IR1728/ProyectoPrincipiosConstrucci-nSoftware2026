package sgpp.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sgpp.modelo.Autoevaluacion;
import sgpp.servicio.AutoevaluacionServicio;
import sgpp.servicio.ContextoAplicacion;
import sgpp.servicio.SesionServicio;

public class EstudianteController extends ControladorBase implements Initializable {

    @FXML private Label lblSesion;
    @FXML private Label lblMensaje;
    @FXML private TextField txtPuntajeAutoevaluacion;
    @FXML private TextArea txtComentariosAutoevaluacion;

    private final SesionServicio sesionServicio = ContextoAplicacion.getSesionServicio();
    private final AutoevaluacionServicio autoevaluacionServicio = ContextoAplicacion.getAutoevaluacionServicio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblSesion.setText(sesionServicio.getUsuarioActual().getNombreUsuario() + " - Estudiante");
    }

    @FXML
    private void registrarAutoevaluacion() {
        try {
            Integer puntaje = Integer.valueOf(txtPuntajeAutoevaluacion.getText());
            if (confirmar("Confirmar autoevaluacion", "Registrar la autoevaluacion capturada?")) {
                Autoevaluacion autoevaluacion = autoevaluacionServicio.registrarAutoevaluacion(
                        puntaje, txtComentariosAutoevaluacion.getText());
                lblMensaje.setText("CU15 completado. Autoevaluacion registrada con folio "
                        + autoevaluacion.getIdAutoevaluacion() + ".");
                txtPuntajeAutoevaluacion.clear();
                txtComentariosAutoevaluacion.clear();
            }
        } catch (RuntimeException ex) {
            lblMensaje.setText(ex.getMessage());
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void cerrarSesion() {
        sesionServicio.cerrarSesion();
        cambiarVista(lblMensaje, "/sgpp/vista/FXMLLogin.fxml");
    }
}
