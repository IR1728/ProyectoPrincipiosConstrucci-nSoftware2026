package sgpp.modelo;

import java.time.LocalDate;

/**
 * Modelo de la tabla proyecto.
 */
public class Proyecto {

    private Integer idProyecto;
    private String nombre;
    private String objetivoGeneral;
    private String metodologia;
    private Integer maxParticipantes;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer idOrganizacionVinculada;
    private Integer idResponsable;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto, String nombre, String objetivoGeneral, String metodologia,
            Integer maxParticipantes, LocalDate fechaInicio, LocalDate fechaFin,
            Integer idOrganizacionVinculada, Integer idResponsable) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.objetivoGeneral = objetivoGeneral;
        this.metodologia = metodologia;
        this.maxParticipantes = maxParticipantes;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idOrganizacionVinculada = idOrganizacionVinculada;
        this.idResponsable = idResponsable;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivoGeneral() {
        return objetivoGeneral;
    }

    public void setObjetivoGeneral(String objetivoGeneral) {
        this.objetivoGeneral = objetivoGeneral;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public Integer getMaxParticipantes() {
        return maxParticipantes;
    }

    public void setMaxParticipantes(Integer maxParticipantes) {
        this.maxParticipantes = maxParticipantes;
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

    public Integer getIdOrganizacionVinculada() {
        return idOrganizacionVinculada;
    }

    public void setIdOrganizacionVinculada(Integer idOrganizacionVinculada) {
        this.idOrganizacionVinculada = idOrganizacionVinculada;
    }

    public Integer getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
