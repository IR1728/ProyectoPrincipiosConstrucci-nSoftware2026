package sgpp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sgpp.utilidad.CargadorVista;

public class SistemaGestionPracticasProfesionales extends Application {

    @Override
    public void start(Stage ventanaPrincipal) throws Exception {
        CargadorVista.establecerVentanaPrincipal(ventanaPrincipal);
        FXMLLoader cargador = new FXMLLoader(
                getClass().getResource("/sgpp/vista/FXML_InicioSesion.fxml"));
        Parent raiz = cargador.load();
        ventanaPrincipal.setScene(new Scene(raiz));
        ventanaPrincipal.setTitle("Sistema de Gestion de Practicas Profesionales");
        ventanaPrincipal.setResizable(true);
        ventanaPrincipal.show();
    }

    public static void main(String[] argumentos) {
        launch(argumentos);
    }
}
