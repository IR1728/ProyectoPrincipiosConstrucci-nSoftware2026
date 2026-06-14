package sgpp.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sgpp.modelo.RolUsuario;
import sgpp.modelo.Usuario;
import sgpp.servicio.ContextoAplicacion;
import sgpp.servicio.SesionServicio;

public class LoginController extends ControladorBase implements Initializable {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private Label lblMensaje;

    private final SesionServicio sesionServicio = ContextoAplicacion.getSesionServicio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtUsuario.setText("coordinador");
        txtContrasena.setText("1234");
        lblMensaje.setText("Usuarios: coordinador, estudiante, profesor. Contrasena: 1234.");
    }

    @FXML
    private void iniciarSesion() {
        try {
            Usuario usuario = sesionServicio.iniciarSesion(txtUsuario.getText(), txtContrasena.getText());
            RolUsuario rol = RolUsuario.desdeTexto(usuario.getRol());
            if (RolUsuario.COORDINADOR.equals(rol)) {
                cambiarVista(lblMensaje, "/sgpp/vista/FXMLCoordinador.fxml");
            } else if (RolUsuario.ESTUDIANTE.equals(rol)) {
                cambiarVista(lblMensaje, "/sgpp/vista/FXMLEstudiante.fxml");
            } else if (RolUsuario.PROFESOR.equals(rol)) {
                cambiarVista(lblMensaje, "/sgpp/vista/FXMLProfesor.fxml");
            }
        } catch (RuntimeException ex) {
            lblMensaje.setText(ex.getMessage());
            mostrarError(ex.getMessage());
        }
    }
}
