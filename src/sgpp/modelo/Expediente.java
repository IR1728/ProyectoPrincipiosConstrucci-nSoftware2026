package sgpp.modelo;

/**
 * Modelo de la tabla expediente.
 */
public class Expediente {

    private Integer idExpediente;
    private byte[] documento;
    private String estado;
    private Integer idEstudiante;
    private Integer idPeriodo;

    public Expediente() {
    }

    public Expediente(Integer idExpediente, byte[] documento, String estado, Integer idEstudiante,
            Integer idPeriodo) {
        this.idExpediente = idExpediente;
        this.documento = documento;
        this.estado = estado;
        this.idEstudiante = idEstudiante;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
