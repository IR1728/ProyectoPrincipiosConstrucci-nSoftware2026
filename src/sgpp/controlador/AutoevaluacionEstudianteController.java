package sgpp.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sgpp.modelo.dao.AutoevaluacionDAO;
import sgpp.modelo.dao.EstudianteDAO;
import sgpp.modelo.dao.OrganizacionVinculadaDAO;
import sgpp.modelo.dao.ProyectoDAO;
import sgpp.modelo.pojo.Autoevaluacion;
import sgpp.modelo.pojo.Estudiante;
import sgpp.modelo.pojo.OrganizacionVinculada;
import sgpp.modelo.pojo.Proyecto;
import sgpp.utilidad.CargadorVista;
import sgpp.utilidad.SesionActiva;
import sgpp.utilidad.Utilidad;

public class AutoevaluacionEstudianteController implements Initializable {

    @FXML
    private Label etiquetaEstudiante;
    @FXML
    private Label etiquetaProyecto;
    @FXML
    private Label etiquetaOrganizacion;
    @FXML
    private VBox contenedorRubrica;
    @FXML
    private TextArea campoComentario;

    private static final String[] CRITERIOS = {
        "Cumplimiento de los objetivos del proyecto",
        "Responsabilidad y puntualidad",
        "Aplicacion de los conocimientos adquiridos",
        "Trabajo en equipo y comunicacion",
        "Atencion del Responsable Tecnico (Organizacion Vinculada)",
        "Ambiente y condiciones de trabajo (Organizacion Vinculada)"
    };

    private final List<ToggleGroup> gruposCriterios = new ArrayList<>();
    private Estudiante estudiante;

    @Override
    public void initialize(URL ubicacion, ResourceBundle recursos) {
        construirRubrica();
        try {
            cargarDatosGenerales();
            if (estudiante != null
                    && AutoevaluacionDAO.existe(estudiante.getMatricula())) {
                Utilidad.mostrarAdvertencia("Autoevaluacion ya registrada",
                        "El expediente ya cuenta con una autoevaluacion.");
                CargadorVista.cambiarVista("FXML_MenuEstudiante.fxml");
            }
        } catch (SQLException excepcion) {
            mostrarErrorConexion(excepcion);
        }
    }

    private void cargarDatosGenerales() throws SQLException {
        String nombreEstudiante = SesionActiva.getUsuarioActual() != null
                ? SesionActiva.getUsuarioActual().getNombreCompleto() : "";
        estudiante = EstudianteDAO.obtenerPorNombre(nombreEstudiante);

        if (estudiante == null) {
            etiquetaEstudiante.setText("Estudiante: " + nombreEstudiante);
            etiquetaProyecto.setText("Proyecto: (sin proyecto asignado)");
            etiquetaOrganizacion.setText("");
            return;
        }

        etiquetaEstudiante.setText("Estudiante: " + estudiante.getNombre()
                + "   Matricula: " + estudiante.getMatricula());

        Proyecto proyecto = ProyectoDAO.obtenerPorIdentificador(
                estudiante.getIdentificadorProyectoAsignado());
        if (proyecto != null) {
            etiquetaProyecto.setText("Proyecto: " + proyecto.getNombre());
            OrganizacionVinculada organizacion =
                    OrganizacionVinculadaDAO.obtenerPorIdentificador(
                            proyecto.getIdentificadorOrganizacionVinculada());
            etiquetaOrganizacion.setText("Organizacion Vinculada: "
                    + (organizacion != null ? organizacion.getNombre() : ""));
        } else {
            etiquetaProyecto.setText("Proyecto: (sin proyecto asignado)");
            etiquetaOrganizacion.setText("");
        }
    }

    private void construirRubrica() {
        for (String criterio : CRITERIOS) {
            ToggleGroup grupo = new ToggleGroup();
            gruposCriterios.add(grupo);

            Label etiquetaCriterio = new Label(criterio);
            etiquetaCriterio.setPrefWidth(420);
            etiquetaCriterio.setWrapText(true);

            HBox fila = new HBox(12);
            fila.setAlignment(Pos.CENTER_LEFT);
            fila.getChildren().add(etiquetaCriterio);

            for (int valor = 1; valor <= 4; valor++) {
                RadioButton opcion = new RadioButton(String.valueOf(valor));
                opcion.setToggleGroup(grupo);
                opcion.setUserData(valor);
                fila.getChildren().add(opcion);
            }
            contenedorRubrica.getChildren().add(fila);
        }
    }

    @FXML
    private void clicSubir() {
        List<Integer> calificaciones = new ArrayList<>();
        for (ToggleGroup grupo : gruposCriterios) {
            Toggle seleccion = grupo.getSelectedToggle();
            if (seleccion == null) {
                Utilidad.mostrarAdvertencia("Rubrica incompleta",
                        "Responde todos los criterios de la autoevaluacion.");
                return;
            }
            calificaciones.add((Integer) seleccion.getUserData());
        }

        boolean confirmado = Utilidad.mostrarConfirmacion("Confirmar",
                "Estas seguro de que deseas subir la autoevaluacion? "
                + "Verifica que los datos sean correctos.");
        if (!confirmado) {
            return;
        }

        String matricula = estudiante != null ? estudiante.getMatricula() : "sin-matricula";
        try {
            if (AutoevaluacionDAO.existe(matricula)) {
                Utilidad.mostrarAdvertencia("Autoevaluacion ya registrada",
                        "El expediente ya cuenta con una autoevaluacion.");
                CargadorVista.cambiarVista("FXML_MenuEstudiante.fxml");
                return;
            }
            AutoevaluacionDAO.registrar(new Autoevaluacion(matricula, calificaciones,
                    campoComentario.getText().trim()));
            Utilidad.mostrarExito("Autoevaluacion registrada",
                    "La autoevaluacion ha sido registrada en el expediente.");
            CargadorVista.cambiarVista("FXML_MenuEstudiante.fxml");
        } catch (SQLException excepcion) {
            mostrarErrorConexion(excepcion);
        }
    }

    @FXML
    private void clicCancelar() {
        boolean confirmado = Utilidad.mostrarConfirmacion("Cancelar",
                "Estas seguro de que deseas cancelar?");
        if (confirmado) {
            CargadorVista.cambiarVista("FXML_MenuEstudiante.fxml");
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
