package sgpp.utilidad;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class CargadorVista {

    private static final String RUTA_BASE = "/sgpp/vista/";

    private static Stage ventanaPrincipal;

    private CargadorVista() {
    }

    public static void establecerVentanaPrincipal(Stage ventana) {
        ventanaPrincipal = ventana;
    }

    public static Stage obtenerVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public static void cambiarVista(String nombreArchivoFxml) {
        try {
            FXMLLoader cargador = new FXMLLoader(
                    CargadorVista.class.getResource(RUTA_BASE + nombreArchivoFxml));
            Parent raiz = cargador.load();
            colocarEscena(raiz);
        } catch (IOException excepcion) {
            Utilidad.mostrarError("Error de navegacion",
                    "No fue posible abrir la ventana: " + nombreArchivoFxml
                    + "\n" + excepcion.getMessage());
        }
    }

    private static void colocarEscena(Parent raiz) {
        Scene escena = ventanaPrincipal.getScene();
        if (escena == null) {
            ventanaPrincipal.setScene(new Scene(raiz));
        } else {
            escena.setRoot(raiz);
        }
        ventanaPrincipal.sizeToScene();
        ventanaPrincipal.centerOnScreen();
    }
}
