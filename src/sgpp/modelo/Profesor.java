package sgpp.modelo;

/**
 * Modelo de la tabla profesor.
 */
public class Profesor {

    private Integer idProfesor;
    private Integer numPersonal;
    private String nombre;
    private String especialidad;
    private Integer idExperienciaEducativa;
    private Integer idUsuario;

    public Profesor() {
    }

    public Profesor(Integer idProfesor, Integer numPersonal, String nombre, String especialidad,
            Integer idExperienciaEducativa, Integer idUsuario) {
        this.idProfesor = idProfesor;
        this.numPersonal = numPersonal;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idUsuario = idUsuario;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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
