package sgpp.utilidad;

import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

public final class Utilidad {

    private Utilidad() {
    }

    public static void mostrarExito(String titulo, String mensaje) {
        mostrar(Alert.AlertType.INFORMATION, titulo, mensaje);
    }

    public static void mostrarError(String titulo, String mensaje) {
        mostrar(Alert.AlertType.ERROR, titulo, mensaje);
    }

    public static void mostrarAdvertencia(String titulo, String mensaje) {
        mostrar(Alert.AlertType.WARNING, titulo, mensaje);
    }

    private static void mostrar(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static boolean mostrarConfirmacion(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, mensaje,
                ButtonType.YES, ButtonType.NO);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        Optional<ButtonType> respuesta = alerta.showAndWait();
        return respuesta.isPresent() && respuesta.get() == ButtonType.YES;
    }

    public static void impedirFechasPasadas(DatePicker... selectores) {
        for (DatePicker selector : selectores) {
            selector.setDayCellFactory(parametro -> new DateCell() {
                @Override
                public void updateItem(LocalDate fecha, boolean vacio) {
                    super.updateItem(fecha, vacio);
                    if (fecha != null && fecha.isBefore(LocalDate.now())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ececec;");
                    }
                }
            });
        }
    }
}
