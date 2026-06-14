package sgpp.modelo;

import java.time.LocalDateTime;

public class Autoevaluacion {

    private Integer idAutoevaluacion;
    private Integer idEstudiante;
    private Integer idPeriodo;
    private Integer puntaje;
    private String comentarios;
    private LocalDateTime fechaRegistro;

    public Autoevaluacion() {
    }

    public Autoevaluacion(Integer idAutoevaluacion, Integer idEstudiante, Integer idPeriodo,
            Integer puntaje, String comentarios, LocalDateTime fechaRegistro) {
        this.idAutoevaluacion = idAutoevaluacion;
        this.idEstudiante = idEstudiante;
        this.idPeriodo = idPeriodo;
        this.puntaje = puntaje;
        this.comentarios = comentarios;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdAutoevaluacion() {
        return idAutoevaluacion;
    }

    public void setIdAutoevaluacion(Integer idAutoevaluacion) {
        this.idAutoevaluacion = idAutoevaluacion;
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

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
