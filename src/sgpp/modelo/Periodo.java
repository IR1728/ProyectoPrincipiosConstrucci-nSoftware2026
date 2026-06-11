package sgpp.modelo;

import java.time.LocalDate;

/**
 * Modelo de la tabla periodo.
 */
public class Periodo {

    private Integer idPeriodo;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Periodo() {
    }

    public Periodo(Integer idPeriodo, String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idPeriodo = idPeriodo;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
