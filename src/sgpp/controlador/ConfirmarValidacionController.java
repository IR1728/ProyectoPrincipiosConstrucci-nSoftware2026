package sgpp.controlador;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sgpp.modelo.dao.ReporteMensualDAO;
import sgpp.modelo.pojo.ReporteMensual;
import sgpp.utilidad.Utilidad;

public class ConfirmarValidacionController {

    @FXML
    private Label etiquetaEstudiante;
    @FXML
    private Label etiquetaDatos;
    @FXML
    private TextArea campoContenido;
    @FXML
    private TextArea campoObservaciones;

    private ReporteMensual reporte;

    public void establecerReporte(ReporteMensual reporte) {
        this.reporte = reporte;
        etiquetaEstudiante.setText("Estudiante: " + reporte.getNombreEstudiante());
        etiquetaDatos.setText("Reporte " + reporte.getNumeroReporte() + " - "
                + reporte.getMes() + " | Fecha: " + reporte.getFechaEntrega()
                + " | Horas: " + reporte.getHorasReportadas());
        campoContenido.setText(reporte.getContenido());
    }

    @FXML
    private void clicValidar() {
        try {
            ReporteMensualDAO.validar(reporte, campoObservaciones.getText().trim());
            Utilidad.mostrarExito("Exito", "Reporte validado con exito.");
            cerrarVentana();
        } catch (SQLException excepcion) {
            mostrarErrorConexion(excepcion);
        }
    }

    @FXML
    private void clicRechazar() {
        boolean confirmado = Utilidad.mostrarConfirmacion("Rechazar reporte",
                "Estas seguro de que deseas rechazar este reporte?");
        if (!confirmado) {
            return;
        }
        try {
            ReporteMensualDAO.rechazar(reporte, campoObservaciones.getText().trim());
            Utilidad.mostrarExito("Reporte rechazado",
                    "El reporte fue marcado como rechazado.");
            cerrarVentana();
        } catch (SQLException excepcion) {
            mostrarErrorConexion(excepcion);
        }
    }

    @FXML
    private void clicRegresar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage ventana = (Stage) campoObservaciones.getScene().getWindow();
        ventana.close();
    }

    private void mostrarErrorConexion(SQLException excepcion) {
        Utilidad.mostrarError("Error de conexion",
                "No hay conexion con la base de datos.\n" + excepcion.getMessage());
    }
}
