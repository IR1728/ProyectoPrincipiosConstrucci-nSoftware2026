package sgpp.modelo;

import java.time.LocalDateTime;

/**
 * Modelo de la tabla rubrica_presentacion.
 */
public class RubricaPresentacion {

    private Integer idPresentacion;
    private LocalDateTime fechaHora;
    private Float calificacion;
    private String observaciones;
    private Integer idEstudiante;
    private Integer idPeriodo;
    private Integer idProfesor;

    public RubricaPresentacion() {
    }

    public RubricaPresentacion(Integer idPresentacion, LocalDateTime fechaHora, Float calificacion,
            String observaciones, Integer idEstudiante, Integer idPeriodo, Integer idProfesor) {
        this.idPresentacion = idPresentacion;
        this.fechaHora = fechaHora;
        this.calificacion = calificacion;
        this.observaciones = observaciones;
        this.idEstudiante = idEstudiante;
        this.idPeriodo = idPeriodo;
        this.idProfesor = idProfesor;
    }

    public Integer getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(Integer idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }
}
