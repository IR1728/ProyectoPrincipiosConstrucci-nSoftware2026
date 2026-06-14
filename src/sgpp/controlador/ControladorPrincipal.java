package sgpp.controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sgpp.modelo.Autoevaluacion;
import sgpp.modelo.Estudiante;
import sgpp.modelo.OrganizacionVinculada;
import sgpp.modelo.ProgramacionEntregas;
import sgpp.modelo.Proyecto;
import sgpp.modelo.ReporteMensual;
import sgpp.modelo.ResponsableTecnico;
import sgpp.modelo.RolUsuario;
import sgpp.modelo.Usuario;
import sgpp.servicio.AsignacionServicio;
import sgpp.servicio.AutoevaluacionServicio;
import sgpp.servicio.ContextoAplicacion;
import sgpp.servicio.EntregaServicio;
import sgpp.servicio.ProyectoServicio;
import sgpp.servicio.ReporteServicio;
import sgpp.servicio.SesionServicio;

public class ControladorPrincipal implements Initializable {

    @FXML private VBox panelLogin;
    @FXML private VBox panelCoordinador;
    @FXML private VBox panelEstudiante;
    @FXML private VBox panelProfesor;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
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

    @FXML private TextField txtPuntajeAutoevaluacion;
    @FXML private TextArea txtComentariosAutoevaluacion;

    @FXML private ListView<ReporteMensual> lstReportesPendientes;
    @FXML private TextArea txtObservacionesReporte;

    private final SesionServicio sesionServicio = ContextoAplicacion.getSesionServicio();
    private final ProyectoServicio proyectoServicio = ContextoAplicacion.getProyectoServicio();
    private final AsignacionServicio asignacionServicio = ContextoAplicacion.getAsignacionServicio();
    private final EntregaServicio entregaServicio = ContextoAplicacion.getEntregaServicio();
    private final AutoevaluacionServicio autoevaluacionServicio = ContextoAplicacion.getAutoevaluacionServicio();
    private final ReporteServicio reporteServicio = ContextoAplicacion.getReporteServicio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ocultarPanelesDeRol();
        txtUsuario.setText("coordinador");
        txtContrasena.setText("1234");
        lblSesion.setText("Sin sesión");
        lblMensaje.setText("Usuarios de prueba: coordinador, estudiante, profesor. Contraseña: 1234.");
    }

    @FXML
    private void iniciarSesion() {
        try {
            Usuario usuario = sesionServicio.iniciarSesion(txtUsuario.getText(), txtContrasena.getText());
            lblSesion.setText(usuario.getNombreUsuario() + " - " + usuario.getRol());
            lblMensaje.setText("Sesión iniciada correctamente.");
            mostrarPanelPorRol(RolUsuario.desdeTexto(usuario.getRol()));
        } catch (RuntimeException ex) {
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void cerrarSesion() {
        sesionServicio.cerrarSesion();
        ocultarPanelesDeRol();
        panelLogin.setVisible(true);
        panelLogin.setManaged(true);
        lblSesion.setText("Sin sesión");
        lblMensaje.setText("Sesión cerrada.");
    }

    @FXML
    private void registrarProyecto() {
        try {
            Proyecto proyecto = new Proyecto();
            proyecto.setNombre(txtNombreProyecto.getText());
            proyecto.setObjetivoGeneral(txtObjetivoProyecto.getText());
            proyecto.setMetodologia(txtMetodologiaProyecto.getText());
            proyecto.setMaxParticipantes(Integer.valueOf(txtMaxParticipantes.getText()));
            proyecto.setFechaInicio(dpInicioProyecto.getValue());
            proyecto.setFechaFin(dpFinProyecto.getValue());
            if (cmbOrganizacion.getValue() == null || cmbResponsable.getValue() == null) {
                throw new IllegalArgumentException("Seleccione organización y responsable técnico.");
            }
            proyecto.setIdOrganizacionVinculada(cmbOrganizacion.getValue().getIdOrganizacionVinculada());
            proyecto.setIdResponsable(cmbResponsable.getValue().getIdResponsable());
            Proyecto registrado = proyectoServicio.registrarProyecto(proyecto);
            lblMensaje.setText("CU01 completado. Proyecto registrado: " + registrado.getNombre());
            limpiarFormularioProyecto();
            cargarDatosCoordinador();
        } catch (RuntimeException ex) {
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void asignarProyecto() {
        try {
            Estudiante estudiante = lstEstudiantesPendientes.getSelectionModel().getSelectedItem();
            Proyecto proyecto = lstProyectosConCupo.getSelectionModel().getSelectedItem();
            if (confirmar("Confirmar asignación", "¿Asignar el proyecto seleccionado al estudiante?")) {
                asignacionServicio.asignarProyecto(estudiante, proyecto);
                lblMensaje.setText("CU07 completado. Proyecto asignado y expediente creado.");
                cargarDatosCoordinador();
            }
        } catch (RuntimeException ex) {
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
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void registrarAutoevaluacion() {
        try {
            Integer puntaje = Integer.valueOf(txtPuntajeAutoevaluacion.getText());
            if (confirmar("Confirmar autoevaluación", "¿Registrar la autoevaluación capturada?")) {
                Autoevaluacion autoevaluacion = autoevaluacionServicio.registrarAutoevaluacion(
                        puntaje, txtComentariosAutoevaluacion.getText());
                lblMensaje.setText("CU15 completado. Autoevaluación registrada con folio "
                        + autoevaluacion.getIdAutoevaluacion() + ".");
                txtPuntajeAutoevaluacion.clear();
                txtComentariosAutoevaluacion.clear();
            }
        } catch (RuntimeException ex) {
            mostrarError(ex.getMessage());
        }
    }

    @FXML
    private void aceptarReporte() {
        validarReporte(true);
    }

    @FXML
    private void rechazarReporte() {
        validarReporte(false);
    }

    private void validarReporte(boolean aceptar) {
        try {
            ReporteMensual reporte = lstReportesPendientes.getSelectionModel().getSelectedItem();
            String accion = aceptar ? "aceptar" : "rechazar";
            if (confirmar("Confirmar validación", "¿Desea " + accion + " el reporte seleccionado?")) {
                if (aceptar) {
                    reporteServicio.aceptar(reporte);
                    lblMensaje.setText("CU18 completado. Reporte aceptado.");
                } else {
                    reporteServicio.rechazar(reporte);
                    lblMensaje.setText("CU18 completado. Reporte rechazado.");
                }
                txtObservacionesReporte.clear();
                cargarDatosProfesor();
            }
        } catch (RuntimeException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void mostrarPanelPorRol(RolUsuario rol) {
        ocultarPanelesDeRol();
        panelLogin.setVisible(false);
        panelLogin.setManaged(false);
        if (RolUsuario.COORDINADOR.equals(rol)) {
            panelCoordinador.setVisible(true);
            panelCoordinador.setManaged(true);
            cargarDatosCoordinador();
        } else if (RolUsuario.ESTUDIANTE.equals(rol)) {
            panelEstudiante.setVisible(true);
            panelEstudiante.setManaged(true);
        } else if (RolUsuario.PROFESOR.equals(rol)) {
            panelProfesor.setVisible(true);
            panelProfesor.setManaged(true);
            cargarDatosProfesor();
        }
    }

    private void cargarDatosCoordinador() {
        cmbOrganizacion.setItems(FXCollections.observableArrayList(proyectoServicio.listarOrganizaciones()));
        cmbResponsable.setItems(FXCollections.observableArrayList(proyectoServicio.listarResponsables()));
        lstEstudiantesPendientes.setItems(FXCollections.observableArrayList(
                asignacionServicio.listarEstudiantesPendientes()));
        lstProyectosConCupo.setItems(FXCollections.observableArrayList(
                asignacionServicio.listarProyectosConCupo()));
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

    private void cargarDatosProfesor() {
        lstReportesPendientes.setItems(FXCollections.observableArrayList(
                reporteServicio.listarReportesPendientes()));
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

    private void ocultarPanelesDeRol() {
        ocultar(panelCoordinador);
        ocultar(panelEstudiante);
        ocultar(panelProfesor);
    }

    private void ocultar(VBox panel) {
        panel.setVisible(false);
        panel.setManaged(false);
    }

    private boolean confirmar(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.YES, ButtonType.NO);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        return alerta.showAndWait().orElse(ButtonType.NO) == ButtonType.YES;
    }

    private void mostrarError(String mensaje) {
        lblMensaje.setText(mensaje);
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje);
        alerta.setTitle("Validación");
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
