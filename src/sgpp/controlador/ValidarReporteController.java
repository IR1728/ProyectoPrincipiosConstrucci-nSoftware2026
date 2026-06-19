package sgpp.controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sgpp.modelo.dao.ReporteMensualDAO;
import sgpp.modelo.pojo.EstadoReporte;
import sgpp.modelo.pojo.ReporteMensual;
import sgpp.utilidad.CargadorVista;
import sgpp.utilidad.Utilidad;

public class ValidarReporteController implements Initializable {

    @FXML
    private TableView<ReporteMensual> tablaReportes;
    @FXML
    private TableColumn<ReporteMensual, String> columnaEstudiante;
    @FXML
    private TableColumn<ReporteMensual, Integer> columnaNumeroReporte;
    @FXML
    private TableColumn<ReporteMensual, String> columnaMes;
    @FXML
    private TableColumn<ReporteMensual, LocalDate> columnaFecha;
    @FXML
    private TableColumn<ReporteMensual, Integer> columnaHoras;
    @FXML
    private TableColumn<ReporteMensual, EstadoReporte> columnaEstado;

    @Override
    public void initialize(URL ubicacion, ResourceBundle recursos) {
        columnaEstudiante.setCellValueFactory(new PropertyValueFactory<>("nombreEstudiante"));
        columnaNumeroReporte.setCellValueFactory(new PropertyValueFactory<>("numeroReporte"));
        columnaMes.setCellValueFactory(new PropertyValueFactory<>("mes"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));
        columnaHoras.setCellValueFactory(new PropertyValueFactory<>("horasReportadas"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        cargarReportes();
    }

    private void cargarReportes() {
        try {
            tablaReportes.setItems(
                    FXCollections.observableArrayList(ReporteMensualDAO.obtenerPendientes()));
        } catch (SQLException excepcion) {
            Utilidad.mostrarError("Error de conexion",
                    "No hay conexion con la base de datos.\n" + excepcion.getMessage());
        }
    }

    @FXML
    private void clicSeleccionar() {
        ReporteMensual reporte = tablaReportes.getSelectionModel().getSelectedItem();
        if (reporte == null) {
            Utilidad.mostrarAdvertencia("Sin seleccion",
                    "Selecciona un reporte de la lista para validarlo.");
            return;
        }
        abrirVentanaConfirmacion(reporte);
        cargarReportes();
    }

    private void abrirVentanaConfirmacion(ReporteMensual reporte) {
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource(
                    "/sgpp/vista/FXML_ConfirmarValidacion.fxml"));
            Parent raiz = cargador.load();
            ConfirmarValidacionController controlador = cargador.getController();
            controlador.establecerReporte(reporte);

            Stage ventana = new Stage();
            ventana.initModality(Modality.APPLICATION_MODAL);
            ventana.setTitle("Validacion de Reporte Mensual");
            ventana.setResizable(false);
            ventana.setScene(new Scene(raiz));
            ventana.showAndWait();
        } catch (IOException excepcion) {
            Utilidad.mostrarError("Error",
                    "No fue posible abrir la ventana de validacion: "
                    + excepcion.getMessage());
        }
    }

    @FXML
    private void clicRegresar() {
        CargadorVista.cambiarVista("FXML_MenuProfesor.fxml");
    }

    @FXML
    private void clicSalir() {
        Platform.exit();
    }
}
