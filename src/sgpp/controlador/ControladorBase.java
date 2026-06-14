package sgpp.controlador;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public abstract class ControladorBase {

    protected void cambiarVista(Label referencia, String rutaFxml) {
        try {
            Stage stage = (Stage) referencia.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(rutaFxml));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            mostrarError("No se pudo cargar la vista: " + rutaFxml);
        }
    }

    protected boolean confirmar(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.YES, ButtonType.NO);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        return alerta.showAndWait().orElse(ButtonType.NO) == ButtonType.YES;
    }

    protected void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje);
        alerta.setTitle("Validacion");
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
