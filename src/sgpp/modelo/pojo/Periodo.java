package sgpp.modelo.pojo;

import java.time.LocalDate;

public class Periodo {

    private int identificador;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Periodo() {
    }

    public Periodo(int identificador, String nombre, LocalDate fechaInicio,
            LocalDate fechaFin) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
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
