package sgpp.modelo;

import java.time.LocalDateTime;

/**
 * Modelo de la tabla reporte_mensual.
 */
public class ReporteMensual {

    private Integer idReporteMensual;
    private LocalDateTime fechaEntrega;
    private Integer numeroMes;
    private String descripcion;
    private byte[] archivo;
    private String estado;
    private Integer idEntregaReporte;
    private Integer idEstudiante;
    private Integer idPeriodo;

    public ReporteMensual() {
    }

    public ReporteMensual(Integer idReporteMensual, LocalDateTime fechaEntrega, Integer numeroMes,
            String descripcion, byte[] archivo, String estado, Integer idEntregaReporte, Integer idEstudiante,
            Integer idPeriodo) {
        this.idReporteMensual = idReporteMensual;
        this.fechaEntrega = fechaEntrega;
        this.numeroMes = numeroMes;
        this.descripcion = descripcion;
        this.archivo = archivo;
        this.estado = estado;
        this.idEntregaReporte = idEntregaReporte;
        this.idEstudiante = idEstudiante;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdReporteMensual() {
        return idReporteMensual;
    }

    public void setIdReporteMensual(Integer idReporteMensual) {
        this.idReporteMensual = idReporteMensual;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getNumeroMes() {
        return numeroMes;
    }

    public void setNumeroMes(Integer numeroMes) {
        this.numeroMes = numeroMes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdEntregaReporte() {
        return idEntregaReporte;
    }

    public void setIdEntregaReporte(Integer idEntregaReporte) {
        this.idEntregaReporte = idEntregaReporte;
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
}
