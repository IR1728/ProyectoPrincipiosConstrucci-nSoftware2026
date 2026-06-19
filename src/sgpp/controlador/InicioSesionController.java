package sgpp.controlador;

import java.sql.SQLException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sgpp.modelo.dao.UsuarioDAO;
import sgpp.modelo.pojo.Usuario;
import sgpp.utilidad.CargadorVista;
import sgpp.utilidad.SesionActiva;
import sgpp.utilidad.Utilidad;

public class InicioSesionController {

    @FXML
    private TextField campoNumeroPersonal;
    @FXML
    private PasswordField campoContrasena;
    @FXML
    private Label etiquetaError;

    @FXML
    private void clicAcceder() {
        String numeroDePersonal = campoNumeroPersonal.getText().trim();
        String contrasena = campoContrasena.getText();

        if (numeroDePersonal.isEmpty() || contrasena.isEmpty()) {
            etiquetaError.setText("Ingresa tu numero de personal y contrasena.");
            return;
        }

        try {
            Usuario usuario = UsuarioDAO.autenticar(numeroDePersonal, contrasena);
            if (usuario == null) {
                etiquetaError.setText("Credenciales incorrectas.");
                return;
            }
            SesionActiva.setUsuarioActual(usuario);
            etiquetaError.setText("");
            dirigirSegunTipo(usuario);
        } catch (SQLException excepcion) {
            etiquetaError.setText("");
            Utilidad.mostrarError("Error de conexion",
                    "No hay conexion con la base de datos.\n" + excepcion.getMessage());
        }
    }

    @FXML
    private void clicSalir() {
        Platform.exit();
    }

    private void dirigirSegunTipo(Usuario usuario) {
        switch (usuario.getTipoUsuario()) {
            case COORDINADOR:
                CargadorVista.cambiarVista("FXML_MenuCoordinador.fxml");
                break;
            case ESTUDIANTE:
                CargadorVista.cambiarVista("FXML_MenuEstudiante.fxml");
                break;
            case PROFESOR:
                CargadorVista.cambiarVista("FXML_MenuProfesor.fxml");
                break;
            default:
                etiquetaError.setText("Tipo de usuario no reconocido.");
        }
    }
}
