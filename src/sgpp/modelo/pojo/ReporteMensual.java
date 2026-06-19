package sgpp.modelo.pojo;

import java.time.LocalDate;

public class ReporteMensual {

    private int identificador;
    private int numeroReporte;
    private String mes;
    private LocalDate fechaEntrega;
    private int horasReportadas;
    private EstadoReporte estado;
    private String observaciones;
    private String nombreEstudiante;
    private String contenido;

    public ReporteMensual() {
    }

    public ReporteMensual(int identificador, int numeroReporte, String mes,
            LocalDate fechaEntrega, int horasReportadas, EstadoReporte estado,
            String nombreEstudiante, String contenido) {
        this.identificador = identificador;
        this.numeroReporte = numeroReporte;
        this.mes = mes;
        this.fechaEntrega = fechaEntrega;
        this.horasReportadas = horasReportadas;
        this.estado = estado;
        this.observaciones = "";
        this.nombreEstudiante = nombreEstudiante;
        this.contenido = contenido;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getNumeroReporte() {
        return numeroReporte;
    }

    public void setNumeroReporte(int numeroReporte) {
        this.numeroReporte = numeroReporte;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getHorasReportadas() {
        return horasReportadas;
    }

    public void setHorasReportadas(int horasReportadas) {
        this.horasReportadas = horasReportadas;
    }

    public EstadoReporte getEstado() {
        return estado;
    }

    public void setEstado(EstadoReporte estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
