package sgpp.modelo;

import java.time.LocalDateTime;

/**
 * Modelo de la tabla entrega_doc_inicial.
 */
public class EntregaDocumentoInicial {

    private Integer idEntregaDocumentoInicial;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaLimite;
    private Boolean abierto;
    private Integer idPeriodo;

    public EntregaDocumentoInicial() {
    }

    public EntregaDocumentoInicial(Integer idEntregaDocumentoInicial, LocalDateTime fechaApertura,
            LocalDateTime fechaLimite, Boolean abierto, Integer idPeriodo) {
        this.idEntregaDocumentoInicial = idEntregaDocumentoInicial;
        this.fechaApertura = fechaApertura;
        this.fechaLimite = fechaLimite;
        this.abierto = abierto;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdEntregaDocumentoInicial() {
        return idEntregaDocumentoInicial;
    }

    public void setIdEntregaDocumentoInicial(Integer idEntregaDocumentoInicial) {
        this.idEntregaDocumentoInicial = idEntregaDocumentoInicial;
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
