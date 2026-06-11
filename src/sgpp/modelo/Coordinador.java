package sgpp.modelo;

/**
 * Modelo de la tabla coordinador.
 */
public class Coordinador {

    private Integer idCoordinador;
    private Integer numPersonal;
    private String nombre;
    private String puesto;
    private Integer idExperienciaEducativa;
    private Integer idUsuario;

    public Coordinador() {
    }

    public Coordinador(Integer idCoordinador, Integer numPersonal, String nombre, String puesto,
            Integer idExperienciaEducativa, Integer idUsuario) {
        this.idCoordinador = idCoordinador;
        this.numPersonal = numPersonal;
        this.nombre = nombre;
        this.puesto = puesto;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idUsuario = idUsuario;
    }

    public Integer getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public Integer getNumPersonal() {
        return numPersonal;
    }

    public void setNumPersonal(Integer numPersonal) {
        this.numPersonal = numPersonal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Integer getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(Integer idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
