package sgpp.servicio;

import java.time.LocalDate;
import sgpp.modelo.ProgramacionEntregas;

public final class ReglasNegocio {

    public static final int HORAS_TOTALES = 200;
    public static final int HORAS_INFORME_PARCIAL = 100;

    private ReglasNegocio() {
    }

    public static void validarTextoObligatorio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio.");
        }
    }

    public static void validarNumeroPositivo(Integer valor, String campo) {
        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser mayor a cero.");
        }
    }

    public static void validarRango(LocalDate apertura, LocalDate limite, String nombre) {
        if (apertura == null || limite == null) {
            throw new IllegalArgumentException("Las fechas de " + nombre + " son obligatorias.");
        }
        if (apertura.isAfter(limite)) {
            throw new IllegalArgumentException("La fecha de apertura de " + nombre
                    + " no puede ser posterior a la fecha límite.");
        }
    }

    public static void validarProgramacionEntregas(ProgramacionEntregas programacion) {
        validarRango(programacion.getAperturaDocumentosIniciales(),
                programacion.getLimiteDocumentosIniciales(), "documentos iniciales");
        validarRango(programacion.getAperturaReportes(),
                programacion.getLimiteReportes(), "reportes");
        validarRango(programacion.getAperturaDocumentosFinales(),
                programacion.getLimiteDocumentosFinales(), "documentos finales");

        if (programacion.getAperturaReportes().isBefore(programacion.getLimiteDocumentosIniciales())) {
            throw new IllegalArgumentException("RN-22: reportes no puede abrir antes del límite de documentos iniciales.");
        }
        if (programacion.getAperturaDocumentosFinales().isBefore(programacion.getLimiteReportes())) {
            throw new IllegalArgumentException("RN-22: documentos finales no puede abrir antes del límite de reportes.");
        }
    }

    public static void validarFechaDentroDeDocumentosFinales(ProgramacionEntregas programacion, LocalDate fecha) {
        if (programacion == null) {
            throw new IllegalArgumentException("No hay programación de entregas activa.");
        }
        if (fecha.isBefore(programacion.getAperturaDocumentosFinales())
                || fecha.isAfter(programacion.getLimiteDocumentosFinales())) {
            throw new IllegalArgumentException("RN-23: la autoevaluación solo se permite dentro de documentos finales.");
        }
    }
}
