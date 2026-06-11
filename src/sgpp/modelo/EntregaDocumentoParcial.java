package sgpp.modelo;

import java.time.LocalDateTime;

/**
 * Modelo de la tabla entrega_doc_parcial.
 */
public class EntregaDocumentoParcial {

    private Integer idEntregaDocumentoParcial;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaLimite;
    private Boolean abierto;
    private Integer idPeriodo;

    public EntregaDocumentoParcial() {
    }

    public EntregaDocumentoParcial(Integer idEntregaDocumentoParcial, LocalDateTime fechaApertura,
            LocalDateTime fechaLimite, Boolean abierto, Integer idPeriodo) {
        this.idEntregaDocumentoParcial = idEntregaDocumentoParcial;
        this.fechaApertura = fechaApertura;
        this.fechaLimite = fechaLimite;
        this.abierto = abierto;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdEntregaDocumentoParcial() {
        return idEntregaDocumentoParcial;
    }

    public void setIdEntregaDocumentoParcial(Integer idEntregaDocumentoParcial) {
        this.idEntregaDocumentoParcial = idEntregaDocumentoParcial;
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
