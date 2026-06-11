package sgpp.modelo;

import java.time.LocalDateTime;

/**
 * Modelo de la tabla entrega_doc_final.
 */
public class EntregaDocumentoFinal {

    private Integer idEntregaDocumentoFinal;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaLimite;
    private Boolean abierto;
    private Integer idPeriodo;

    public EntregaDocumentoFinal() {
    }

    public EntregaDocumentoFinal(Integer idEntregaDocumentoFinal, LocalDateTime fechaApertura,
            LocalDateTime fechaLimite, Boolean abierto, Integer idPeriodo) {
        this.idEntregaDocumentoFinal = idEntregaDocumentoFinal;
        this.fechaApertura = fechaApertura;
        this.fechaLimite = fechaLimite;
        this.abierto = abierto;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdEntregaDocumentoFinal() {
        return idEntregaDocumentoFinal;
    }

    public void setIdEntregaDocumentoFinal(Integer idEntregaDocumentoFinal) {
        this.idEntregaDocumentoFinal = idEntregaDocumentoFinal;
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
