package sgpp.modelo;

import java.time.LocalDateTime;

/**
 * Modelo de la tabla entrega_reporte.
 */
public class EntregaReporte {

    private Integer idEntregaReporte;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaLimite;
    private Boolean abierto;
    private Integer idPeriodo;

    public EntregaReporte() {
    }

    public EntregaReporte(Integer idEntregaReporte, LocalDateTime fechaApertura, LocalDateTime fechaLimite,
            Boolean abierto, Integer idPeriodo) {
        this.idEntregaReporte = idEntregaReporte;
        this.fechaApertura = fechaApertura;
        this.fechaLimite = fechaLimite;
        this.abierto = abierto;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdEntregaReporte() {
        return idEntregaReporte;
    }

    public void setIdEntregaReporte(Integer idEntregaReporte) {
        this.idEntregaReporte = idEntregaReporte;
    }

    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Boolean getAbierto() {
        return abierto;
    }

    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }
}
