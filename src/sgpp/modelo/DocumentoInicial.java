package sgpp.modelo;

import java.time.LocalDateTime;

/**
 * Modelo de la tabla documento_inicial.
 */
public class DocumentoInicial {

    private Integer idDocumentoInicial;
    private LocalDateTime fechaEntrega;
    private String tipo;
    private String estado;
    private byte[] documento;
    private Integer idEntregaDocumentoInicial;
    private Integer idEstudiante;
    private Integer idPeriodo;

    public DocumentoInicial() {
    }

    public DocumentoInicial(Integer idDocumentoInicial, LocalDateTime fechaEntrega, String tipo, String estado,
            byte[] documento, Integer idEntregaDocumentoInicial, Integer idEstudiante, Integer idPeriodo) {
        this.idDocumentoInicial = idDocumentoInicial;
        this.fechaEntrega = fechaEntrega;
        this.tipo = tipo;
        this.estado = estado;
        this.documento = documento;
        this.idEntregaDocumentoInicial = idEntregaDocumentoInicial;
        this.idEstudiante = idEstudiante;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdDocumentoInicial() {
        return idDocumentoInicial;
    }

    public void setIdDocumentoInicial(Integer idDocumentoInicial) {
        this.idDocumentoInicial = idDocumentoInicial;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public Integer getIdEntregaDocumentoInicial() {
        return idEntregaDocumentoInicial;
    }

    public void setIdEntregaDocumentoInicial(Integer idEntregaDocumentoInicial) {
        this.idEntregaDocumentoInicial = idEntregaDocumentoInicial;
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
