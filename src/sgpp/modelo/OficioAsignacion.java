package sgpp.modelo;

/**
 * Modelo de la tabla oficio_asignacion.
 */
public class OficioAsignacion {

    private Integer idOficio;
    private Integer idPeriodo;
    private Integer idEstudiante;
    private byte[] documento;

    public OficioAsignacion() {
    }

    public OficioAsignacion(Integer idOficio, Integer idPeriodo, Integer idEstudiante, byte[] documento) {
        this.idOficio = idOficio;
        this.idPeriodo = idPeriodo;
        this.idEstudiante = idEstudiante;
        this.documento = documento;
    }

    public Integer getIdOficio() {
        return idOficio;
    }

    public void setIdOficio(Integer idOficio) {
        this.idOficio = idOficio;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }
}
