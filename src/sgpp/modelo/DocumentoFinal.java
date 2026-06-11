package sgpp.modelo;

import java.time.LocalDateTime;

/**
 * Modelo de la tabla documento_final.
 */
public class DocumentoFinal {

    private Integer idDocumentoFinal;
    private LocalDateTime fechaEntrega;
    private String tipo;
    private String estado;
    private byte[] documento;
    private Integer idEntregaDocumentoFinal;
    private Integer idEstudiante;
    private Integer idPeriodo;

    public DocumentoFinal() {
    }

    public DocumentoFinal(Integer idDocumentoFinal, LocalDateTime fechaEntrega, String tipo, String estado,
            byte[] documento, Integer idEntregaDocumentoFinal, Integer idEstudiante, Integer idPeriodo) {
        this.idDocumentoFinal = idDocumentoFinal;
        this.fechaEntrega = fechaEntrega;
        this.tipo = tipo;
        this.estado = estado;
        this.documento = documento;
        this.idEntregaDocumentoFinal = idEntregaDocumentoFinal;
        this.idEstudiante = idEstudiante;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdDocumentoFinal() {
        return idDocumentoFinal;
    }

    public void setIdDocumentoFinal(Integer idDocumentoFinal) {
        this.idDocumentoFinal = idDocumentoFinal;
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

    public Integer getIdEntregaDocumentoFinal() {
        return idEntregaDocumentoFinal;
    }

    public void setIdEntregaDocumentoFinal(Integer idEntregaDocumentoFinal) {
        this.idEntregaDocumentoFinal = idEntregaDocumentoFinal;
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
