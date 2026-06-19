package sgpp.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sgpp.modelo.dao.EstudianteDAO;
import sgpp.modelo.dao.ProyectoDAO;
import sgpp.modelo.pojo.Estudiante;
import sgpp.modelo.pojo.Proyecto;
import sgpp.utilidad.CargadorVista;
import sgpp.utilidad.Utilidad;

public class AsignarProyectoController implements Initializable {

    @FXML
    private ComboBox<Estudiante> comboEstudiantes;
    @FXML
    private TableView<Proyecto> tablaProyectos;
    @FXML
    private TableColumn<Proyecto, String> columnaNombreProyecto;
    @FXML
    private TableColumn<Proyecto, Integer> columnaLugaresDisponibles;

    @Override
    public void initialize(URL ubicacion, ResourceBundle recursos) {
        columnaNombreProyecto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaLugaresDisponibles.setCellValueFactory(
                new PropertyValueFactory<>("lugaresDisponibles"));
        cargarDatos();
    }

    private void cargarDatos() {
        try {
            comboEstudiantes.setItems(
                    FXCollections.observableArrayList(EstudianteDAO.obtenerSinProyecto()));
            comboEstudiantes.getSelectionModel().clearSelection();
            tablaProyectos.setItems(FXCollections.observableArrayList(
                    ProyectoDAO.obtenerConLugaresDisponibles()));
        } catch (SQLException excepcion) {
            mostrarErrorConexion(excepcion);
        }
    }

    @FXML
    private void clicAsignar() {
        Estudiante estudiante = comboEstudiantes.getSelectionModel().getSelectedItem();
        Proyecto proyecto = tablaProyectos.getSelectionModel().getSelectedItem();

        if (estudiante == null || proyecto == null) {
            Utilidad.mostrarAdvertencia("Seleccion incompleta",
                    "Selecciona un estudiante y un proyecto para asignar.");
            return;
        }

        boolean confirmado = Utilidad.mostrarConfirmacion("Confirmar asignacion",
                "Estas seguro de que deseas asignar al estudiante " + estudiante.getNombre()
                + " al proyecto " + proyecto.getNombre()
                + "? Esta accion no se puede deshacer.");
        if (!confirmado) {
            return;
        }

        try {
            EstudianteDAO.asignarProyecto(estudiante, proyecto);
            Utilidad.mostrarExito("Asignacion realizada",
                    "Asignacion realizada exitosamente. Se creo el expediente del estudiante.");
            cargarDatos();
        } catch (SQLException excepcion) {
            mostrarErrorConexion(excepcion);
        }
    }

    @FXML
    private void clicCancelar() {
        boolean confirmado = Utilidad.mostrarConfirmacion("Cancelar",
                "Cancelar? Se perdera el progreso no guardado.");
        if (confirmado) {
            CargadorVista.cambiarVista("FXML_MenuCoordinador.fxml");
        }
    }

    @FXML
    private void clicSalir() {
        Platform.exit();
    }

    private void mostrarErrorConexion(SQLException excepcion) {
        Utilidad.mostrarError("Error de conexion",
                "No hay conexion con la base de datos.\n" + excepcion.getMessage());
    }
}
