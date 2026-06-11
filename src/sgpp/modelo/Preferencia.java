package sgpp.modelo;

import java.time.LocalDateTime;

/**
 * Modelo de la tabla preferencia.
 */
public class Preferencia {

    private Integer idPreferencia;
    private LocalDateTime fechaSeleccion;
    private Integer numeroProyecto;
    private Integer idEstudiante;
    private Integer idPeriodo;
    private Integer idProyecto;

    public Preferencia() {
    }

    public Preferencia(Integer idPreferencia, LocalDateTime fechaSeleccion, Integer numeroProyecto,
            Integer idEstudiante, Integer idPeriodo, Integer idProyecto) {
        this.idPreferencia = idPreferencia;
        this.fechaSeleccion = fechaSeleccion;
        this.numeroProyecto = numeroProyecto;
        this.idEstudiante = idEstudiante;
        this.idPeriodo = idPeriodo;
        this.idProyecto = idProyecto;
    }

    public Integer getIdPreferencia() {
        return idPreferencia;
    }

    public void setIdPreferencia(Integer idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    public LocalDateTime getFechaSeleccion() {
        return fechaSeleccion;
    }

    public void setFechaSeleccion(LocalDateTime fechaSeleccion) {
        this.fechaSeleccion = fechaSeleccion;
    }

    public Integer getNumeroProyecto() {
        return numeroProyecto;
    }

    public void setNumeroProyecto(Integer numeroProyecto) {
        this.numeroProyecto = numeroProyecto;
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

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }
}
