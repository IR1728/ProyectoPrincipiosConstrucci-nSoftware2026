package sgpp.modelo;

/**
 * Modelo de la tabla experiencia_educativa.
 */
public class ExperienciaEducativa {

    private Integer idExperienciaEducativa;
    private String nrc;
    private String nombre;
    private Integer idPeriodo;

    public ExperienciaEducativa() {
    }

    public ExperienciaEducativa(Integer idExperienciaEducativa, String nrc, String nombre, Integer idPeriodo) {
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.nrc = nrc;
        this.nombre = nombre;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(Integer idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
