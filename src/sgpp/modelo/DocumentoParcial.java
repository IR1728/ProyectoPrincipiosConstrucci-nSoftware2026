package sgpp.modelo;

import java.time.LocalDateTime;

/**
 * Modelo de la tabla documento_parcial.
 */
public class DocumentoParcial {

    private Integer idDocumentoParcial;
    private LocalDateTime fechaEntrega;
    private String tipo;
    private String estado;
    private byte[] documento;
    private Integer idEntregaDocumentoParcial;
    private Integer idEstudiante;
    private Integer idPeriodo;

    public DocumentoParcial() {
    }

    public DocumentoParcial(Integer idDocumentoParcial, LocalDateTime fechaEntrega, String tipo, String estado,
            byte[] documento, Integer idEntregaDocumentoParcial, Integer idEstudiante, Integer idPeriodo) {
        this.idDocumentoParcial = idDocumentoParcial;
        this.fechaEntrega = fechaEntrega;
        this.tipo = tipo;
        this.estado = estado;
        this.documento = documento;
        this.idEntregaDocumentoParcial = idEntregaDocumentoParcial;
        this.idEstudiante = idEstudiante;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdDocumentoParcial() {
        return idDocumentoParcial;
    }

    public void setIdDocumentoParcial(Integer idDocumentoParcial) {
        this.idDocumentoParcial = idDocumentoParcial;
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

    public Integer getIdEntregaDocumentoParcial() {
        return idEntregaDocumentoParcial;
    }

    public void setIdEntregaDocumentoParcial(Integer idEntregaDocumentoParcial) {
        this.idEntregaDocumentoParcial = idEntregaDocumentoParcial;
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
