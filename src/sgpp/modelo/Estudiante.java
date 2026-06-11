package sgpp.modelo;

import java.math.BigDecimal;

/**
 * Modelo de la tabla estudiante.
 */
public class Estudiante {

    private Integer idEstudiante;
    private String matricula;
    private String nombre;
    private Integer semestre;
    private BigDecimal promedio;
    private Integer idProyecto;
    private Integer idPeriodo;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiante, String matricula, String nombre, Integer semestre,
            BigDecimal promedio, Integer idProyecto, Integer idPeriodo) {
        this.idEstudiante = idEstudiante;
        this.matricula = matricula;
        this.nombre = nombre;
        this.semestre = semestre;
        this.promedio = promedio;
        this.idProyecto = idProyecto;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
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
