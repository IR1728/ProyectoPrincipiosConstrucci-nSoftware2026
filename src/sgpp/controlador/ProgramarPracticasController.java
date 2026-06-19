package sgpp.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import sgpp.modelo.dao.ProgramacionEntregasDAO;
import sgpp.modelo.pojo.ProgramacionEntregas;
import sgpp.utilidad.CargadorVista;
import sgpp.utilidad.Utilidad;

public class ProgramarPracticasController implements Initializable {

    @FXML
    private DatePicker aperturaDocumentoInicial;
    @FXML
    private DatePicker limiteDocumentoInicial;
    @FXML
    private DatePicker aperturaDocumentoParcial;
    @FXML
    private DatePicker limiteDocumentoParcial;
    @FXML
    private DatePicker aperturaDocumentoFinal;
    @FXML
    private DatePicker limiteDocumentoFinal;
    @FXML
    private DatePicker aperturaReporteUno;
    @FXML
    private DatePicker limiteReporteUno;
    @FXML
    private DatePicker aperturaReporteDos;
    @FXML
    private DatePicker limiteReporteDos;
    @FXML
    private DatePicker aperturaReporteTres;
    @FXML
    private DatePicker limiteReporteTres;
    @FXML
    private DatePicker aperturaReporteCuatro;
    @FXML
    private DatePicker limiteReporteCuatro;

    @Override
    public void initialize(URL ubicacion, ResourceBundle recursos) {
        Utilidad.impedirFechasPasadas(
                aperturaDocumentoInicial, limiteDocumentoInicial,
                aperturaDocumentoParcial, limiteDocumentoParcial,
                aperturaDocumentoFinal, limiteDocumentoFinal,
                aperturaReporteUno, limiteReporteUno,
                aperturaReporteDos, limiteReporteDos,
                aperturaReporteTres, limiteReporteTres,
                aperturaReporteCuatro, limiteReporteCuatro);
    }

    @FXML
    private void clicGuardar() {
        if (hayCamposVacios()) {
            Utilidad.mostrarAdvertencia("Campos requeridos",
                    "Debes indicar la fecha de apertura y limite de todas las entregas.");
            return;
        }
        if (!rangosValidos()) {
            return;
        }

        ProgramacionEntregas programacion = new ProgramacionEntregas();
        programacion.setAperturaDocumentoInicial(aperturaDocumentoInicial.getValue());
        programacion.setLimiteDocumentoInicial(limiteDocumentoInicial.getValue());
        programacion.setAperturaDocumentoParcial(aperturaDocumentoParcial.getValue());
        programacion.setLimiteDocumentoParcial(limiteDocumentoParcial.getValue());
        programacion.setAperturaDocumentoFinal(aperturaDocumentoFinal.getValue());
        programacion.setLimiteDocumentoFinal(limiteDocumentoFinal.getValue());
        programacion.setAperturaReporteUno(aperturaReporteUno.getValue());
        programacion.setLimiteReporteUno(limiteReporteUno.getValue());
        programacion.setAperturaReporteDos(aperturaReporteDos.getValue());
        programacion.setLimiteReporteDos(limiteReporteDos.getValue());
        programacion.setAperturaReporteTres(aperturaReporteTres.getValue());
        programacion.setLimiteReporteTres(limiteReporteTres.getValue());
        programacion.setAperturaReporteCuatro(aperturaReporteCuatro.getValue());
        programacion.setLimiteReporteCuatro(limiteReporteCuatro.getValue());

        try {
            ProgramacionEntregasDAO.guardar(programacion);
            Utilidad.mostrarExito("Programacion exitosa",
                    "Entregas del periodo programadas exitosamente.");
            CargadorVista.cambiarVista("FXML_MenuCoordinador.fxml");
        } catch (SQLException excepcion) {
            Utilidad.mostrarError("Error de conexion",
                    "No hay conexion con la base de datos.\n" + excepcion.getMessage());
        }
    }

    private boolean hayCamposVacios() {
        for (DatePicker selector : selectoresEnOrden()) {
            if (selector.getValue() == null) {
                return true;
            }
        }
        return false;
    }

    private DatePicker[] selectoresEnOrden() {
        return new DatePicker[]{
            aperturaDocumentoInicial, limiteDocumentoInicial,
            aperturaDocumentoParcial, limiteDocumentoParcial,
            aperturaDocumentoFinal, limiteDocumentoFinal,
            aperturaReporteUno, limiteReporteUno,
            aperturaReporteDos, limiteReporteDos,
            aperturaReporteTres, limiteReporteTres,
            aperturaReporteCuatro, limiteReporteCuatro
        };
    }

    private boolean rangosValidos() {
        DatePicker[] secuencia = {
            aperturaDocumentoInicial, limiteDocumentoInicial,
            aperturaDocumentoParcial, limiteDocumentoParcial,
            aperturaDocumentoFinal, limiteDocumentoFinal,
            aperturaReporteUno, limiteReporteUno,
            aperturaReporteDos, limiteReporteDos,
            aperturaReporteTres, limiteReporteTres,
            aperturaReporteCuatro, limiteReporteCuatro
        };

        LocalDate fechaPrevia = null;
        for (DatePicker selector : secuencia) {
            LocalDate fecha = selector.getValue();
            if (fecha == null) {
                continue;
            }
            if (fecha.isBefore(LocalDate.now())) {
                Utilidad.mostrarAdvertencia("Fecha invalida",
                        "No se permite elegir una fecha anterior a la actual.");
                return false;
            }
            if (fechaPrevia != null && fecha.isBefore(fechaPrevia)) {
                Utilidad.mostrarAdvertencia("Rangos de fecha invalidos",
                        "Las fechas deben ir en orden cronologico: cada apertura y limite "
                        + "no puede ser anterior a la fecha previa.");
                return false;
            }
            fechaPrevia = fecha;
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
}
