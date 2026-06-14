package sgpp.controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sgpp.modelo.Estudiante;
import sgpp.modelo.OrganizacionVinculada;
import sgpp.modelo.ProgramacionEntregas;
import sgpp.modelo.Proyecto;
import sgpp.modelo.ResponsableTecnico;
import sgpp.servicio.AsignacionServicio;
import sgpp.servicio.ContextoAplicacion;
import sgpp.servicio.EntregaServicio;
import sgpp.servicio.ProyectoServicio;
import sgpp.servicio.SesionServicio;

public class CoordinadorController extends ControladorBase implements Initializable {

    @FXML private Label lblSesion;
    @FXML private Label lblMensaje;
    @FXML private TextField txtNombreProyecto;
    @FXML private TextArea txtObjetivoProyecto;
    @FXML private TextField txtMetodologiaProyecto;
    @FXML private TextField txtMaxParticipantes;
    @FXML private DatePicker dpInicioProyecto;
    @FXML private DatePicker dpFinProyecto;
    @FXML private ComboBox<OrganizacionVinculada> cmbOrganizacion;
    @FXML private ComboBox<ResponsableTecnico> cmbResponsable;
    @FXML private ListView<Estudiante> lstEstudiantesPendientes;
    @FXML private ListView<Proyecto> lstProyectosConCupo;
    @FXML private DatePicker dpInicialApertura;
    @FXML private DatePicker dpInicialLimite;
    @FXML private DatePicker dpReportesApertura;
    @FXML private DatePicker dpReportesLimite;
    @FXML private DatePicker dpFinalApertura;
    @FXML private DatePicker dpFinalLimite;

    private final SesionServicio sesionServicio = ContextoAplicacion.getSesionServicio();
    private final ProyectoServicio proyectoServicio = ContextoAplicacion.getProyectoServicio();
    private final AsignacionServicio asignacionServicio = ContextoAplicacion.getAsignacionServicio();
    private final EntregaServicio entregaServicio = ContextoAplicacion.getEntregaServicio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblSesion.setText(sesionServicio.getUsuarioActual().getNombreUsuario() + " - Coordinador");
        cargarDatos();
    }

    @FXML
    private void registrarProyecto() {
        try {
            if (cmbOrganizacion.getValue() == null || cmbResponsable.getValue() == null) {
                throw new IllegalArgumentException("Seleccione organizacion y responsable tecnico.");
            }
            Proyecto proyecto = new Proyecto();
            proyecto.setNombre(txtNombreProyecto.getText());
            proyecto.setObjetivoGeneral(txtObjetivoProyecto.getText());
            proyecto.setMetodologia(txtMetodologiaProyecto.getText());
            proyecto.setMaxParticipantes(Integer.valueOf(txtMaxParticipantes.getText()));
            proyecto.setFechaInicio(dpInicioProyecto.getValue());
            proyecto.setFechaFin(dpFinProyecto.getValue());
            proyecto.setIdOrganizacionVinculada(cmbOrganizacion.getValue().getIdOrganizacionVinculada());
            proyecto.setIdResponsable(cmbResponsable.getValue().getIdResponsable());
            Proyecto registrado = proyectoServicio.registrarProyecto(proyecto);
            lblMensaje.setText("CU01 completado. Proyecto registrado: " + registrado.getNombre());
            limpiarFormularioProyecto();
            cargarDatos();
        } catch (RuntimeException ex) {
            lblMensaje.setText(ex.getMessage());
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void asignarProyecto() {
        try {
            Estudiante estudiante = lstEstudiantesPendientes.getSelectionModel().getSelectedItem();
            Proyecto proyecto = lstProyectosConCupo.getSelectionModel().getSelectedItem();
            if (confirmar("Confirmar asignacion", "Asignar el proyecto seleccionado al estudiante?")) {
                asignacionServicio.asignarProyecto(estudiante, proyecto);
                lblMensaje.setText("CU07 completado. Proyecto asignado y expediente creado.");
                cargarDatos();
            }
        } catch (RuntimeException ex) {
            lblMensaje.setText(ex.getMessage());
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void programarEntregas() {
        try {
            ProgramacionEntregas programacion = new ProgramacionEntregas();
            programacion.setAperturaDocumentosIniciales(dpInicialApertura.getValue());
            programacion.setLimiteDocumentosIniciales(dpInicialLimite.getValue());
            programacion.setAperturaReportes(dpReportesApertura.getValue());
            programacion.setLimiteReportes(dpReportesLimite.getValue());
            programacion.setAperturaDocumentosFinales(dpFinalApertura.getValue());
            programacion.setLimiteDocumentosFinales(dpFinalLimite.getValue());
            entregaServicio.programarEntregas(programacion);
            lblMensaje.setText("CU11 completado. Entregas programadas.");
        } catch (RuntimeException ex) {
            lblMensaje.setText(ex.getMessage());
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void cerrarSesion() {
        sesionServicio.cerrarSesion();
        cambiarVista(lblMensaje, "/sgpp/vista/FXMLLogin.fxml");
    }

    private void cargarDatos() {
        cmbOrganizacion.setItems(FXCollections.observableArrayList(proyectoServicio.listarOrganizaciones()));
        cmbResponsable.setItems(FXCollections.observableArrayList(proyectoServicio.listarResponsables()));
        lstEstudiantesPendientes.setItems(FXCollections.observableArrayList(asignacionServicio.listarEstudiantesPendientes()));
        lstProyectosConCupo.setItems(FXCollections.observableArrayList(asignacionServicio.listarProyectosConCupo()));
        cargarProgramacion();
    }

    private void cargarProgramacion() {
        ProgramacionEntregas programacion = entregaServicio.obtenerProgramacion();
        if (programacion != null) {
            dpInicialApertura.setValue(programacion.getAperturaDocumentosIniciales());
            dpInicialLimite.setValue(programacion.getLimiteDocumentosIniciales());
            dpReportesApertura.setValue(programacion.getAperturaReportes());
            dpReportesLimite.setValue(programacion.getLimiteReportes());
            dpFinalApertura.setValue(programacion.getAperturaDocumentosFinales());
            dpFinalLimite.setValue(programacion.getLimiteDocumentosFinales());
        } else {
            LocalDate hoy = LocalDate.now();
            dpInicialApertura.setValue(hoy);
            dpInicialLimite.setValue(hoy.plusDays(7));
            dpReportesApertura.setValue(hoy.plusDays(8));
            dpReportesLimite.setValue(hoy.plusDays(30));
            dpFinalApertura.setValue(hoy.plusDays(31));
            dpFinalLimite.setValue(hoy.plusDays(45));
        }
    }

    private void limpiarFormularioProyecto() {
        txtNombreProyecto.clear();
        txtObjetivoProyecto.clear();
        txtMetodologiaProyecto.clear();
        txtMaxParticipantes.clear();
        dpInicioProyecto.setValue(null);
        dpFinProyecto.setValue(null);
        cmbOrganizacion.getSelectionModel().clearSelection();
        cmbResponsable.getSelectionModel().clearSelection();
    }
}
