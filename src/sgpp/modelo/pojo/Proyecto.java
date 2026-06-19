package sgpp.modelo.pojo;

import java.time.LocalDate;

public class Proyecto {

    private int identificador;
    private String nombre;
    private String objetivoGeneral;
    private String metodologia;
    private int numeroMaximoParticipantes;
    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;
    private int identificadorOrganizacionVinculada;
    private int identificadorResponsableTecnico;
    private int lugaresDisponibles;

    public Proyecto() {
    }

    public Proyecto(int identificador, String nombre, String objetivoGeneral,
            String metodologia, int numeroMaximoParticipantes, LocalDate fechaInicio,
            LocalDate fechaFinalizacion, int identificadorOrganizacionVinculada,
            int identificadorResponsableTecnico, int lugaresDisponibles) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.objetivoGeneral = objetivoGeneral;
        this.metodologia = metodologia;
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.identificadorOrganizacionVinculada = identificadorOrganizacionVinculada;
        this.identificadorResponsableTecnico = identificadorResponsableTecnico;
        this.lugaresDisponibles = lugaresDisponibles;
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

    public int getNumeroMaximoParticipantes() {
        return numeroMaximoParticipantes;
    }

    public void setNumeroMaximoParticipantes(int numeroMaximoParticipantes) {
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public int getIdentificadorOrganizacionVinculada() {
        return identificadorOrganizacionVinculada;
    }

    public void setIdentificadorOrganizacionVinculada(int identificadorOrganizacionVinculada) {
        this.identificadorOrganizacionVinculada = identificadorOrganizacionVinculada;
    }

    public int getIdentificadorResponsableTecnico() {
        return identificadorResponsableTecnico;
    }

    public void setIdentificadorResponsableTecnico(int identificadorResponsableTecnico) {
        this.identificadorResponsableTecnico = identificadorResponsableTecnico;
    }

    public int getLugaresDisponibles() {
        return lugaresDisponibles;
    }

    public void setLugaresDisponibles(int lugaresDisponibles) {
        this.lugaresDisponibles = lugaresDisponibles;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
