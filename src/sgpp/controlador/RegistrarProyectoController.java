package sgpp.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sgpp.modelo.dao.OrganizacionVinculadaDAO;
import sgpp.modelo.dao.ProyectoDAO;
import sgpp.modelo.dao.ResponsableTecnicoDAO;
import sgpp.modelo.pojo.OrganizacionVinculada;
import sgpp.modelo.pojo.Proyecto;
import sgpp.modelo.pojo.ResponsableTecnico;
import sgpp.utilidad.CargadorVista;
import sgpp.utilidad.Utilidad;

public class RegistrarProyectoController implements Initializable {

    @FXML
    private TextField campoNombre;
    @FXML
    private TextArea campoObjetivoGeneral;
    @FXML
    private TextField campoMetodologia;
    @FXML
    private TextField campoMaximoParticipantes;
    @FXML
    private DatePicker selectorFechaInicio;
    @FXML
    private DatePicker selectorFechaFinalizacion;
    @FXML
    private ComboBox<OrganizacionVinculada> comboOrganizacionVinculada;
    @FXML
    private ComboBox<ResponsableTecnico> comboResponsableTecnico;

    @Override
    public void initialize(URL ubicacion, ResourceBundle recursos) {
        Utilidad.impedirFechasPasadas(selectorFechaInicio, selectorFechaFinalizacion);
        try {
            comboOrganizacionVinculada.setItems(
                    FXCollections.observableArrayList(OrganizacionVinculadaDAO.obtenerTodas()));
        } catch (SQLException excepcion) {
            mostrarErrorConexion(excepcion);
        }
    }

    @FXML
    private void clicSeleccionarOrganizacion() {
        OrganizacionVinculada organizacion =
                comboOrganizacionVinculada.getSelectionModel().getSelectedItem();
        if (organizacion != null) {
            try {
                comboResponsableTecnico.setItems(FXCollections.observableArrayList(
                        ResponsableTecnicoDAO.obtenerPorOrganizacion(
                                organizacion.getIdentificador())));
                comboResponsableTecnico.getSelectionModel().clearSelection();
            } catch (SQLException excepcion) {
                mostrarErrorConexion(excepcion);
            }
        }
    }

    @FXML
    private void clicRegistrar() {
        if (!camposValidos()) {
            return;
        }

        int maximoParticipantes = Integer.parseInt(campoMaximoParticipantes.getText().trim());
        OrganizacionVinculada organizacion =
                comboOrganizacionVinculada.getSelectionModel().getSelectedItem();
        ResponsableTecnico responsable =
                comboResponsableTecnico.getSelectionModel().getSelectedItem();

        Proyecto proyecto = new Proyecto(0, campoNombre.getText().trim(),
                campoObjetivoGeneral.getText().trim(), campoMetodologia.getText().trim(),
                maximoParticipantes, selectorFechaInicio.getValue(),
                selectorFechaFinalizacion.getValue(), organizacion.getIdentificador(),
                responsable.getIdentificador(), maximoParticipantes);

        try {
            ProyectoDAO.registrar(proyecto);
            Utilidad.mostrarExito("Registro Exitoso",
                    "El proyecto se registro correctamente.");
            CargadorVista.cambiarVista("FXML_MenuCoordinador.fxml");
        } catch (SQLException excepcion) {
            mostrarErrorConexion(excepcion);
        }
    }

    private boolean camposValidos() {
        if (campoNombre.getText().trim().isEmpty()
                || campoObjetivoGeneral.getText().trim().isEmpty()
                || campoMetodologia.getText().trim().isEmpty()
                || campoMaximoParticipantes.getText().trim().isEmpty()
                || selectorFechaInicio.getValue() == null
                || selectorFechaFinalizacion.getValue() == null
                || comboOrganizacionVinculada.getSelectionModel().getSelectedItem() == null
                || comboResponsableTecnico.getSelectionModel().getSelectedItem() == null) {
            Utilidad.mostrarAdvertencia("Campos requeridos",
                    "Completa todos los campos para registrar el proyecto.");
            return false;
        }

        int maximoParticipantes;
        try {
            maximoParticipantes = Integer.parseInt(campoMaximoParticipantes.getText().trim());
        } catch (NumberFormatException excepcion) {
            Utilidad.mostrarAdvertencia("Dato invalido",
                    "El numero maximo de participantes debe ser un numero entero.");
            return false;
        }
        if (maximoParticipantes <= 0) {
            Utilidad.mostrarAdvertencia("Dato invalido",
                    "El numero maximo de participantes debe ser mayor que cero.");
            return false;
        }

        LocalDate fechaInicio = selectorFechaInicio.getValue();
        LocalDate fechaFinalizacion = selectorFechaFinalizacion.getValue();
        if (fechaInicio.isBefore(LocalDate.now())) {
            Utilidad.mostrarAdvertencia("Fechas invalidas",
                    "No se permite elegir una fecha anterior a la actual.");
            return false;
        }
        if (fechaFinalizacion.isBefore(fechaInicio)) {
            Utilidad.mostrarAdvertencia("Fechas invalidas",
                    "La fecha de finalizacion no puede ser anterior a la de inicio.");
            return false;
        }
        return true;
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
