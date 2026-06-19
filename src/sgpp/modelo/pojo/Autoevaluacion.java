package sgpp.modelo.pojo;

import java.util.List;

public class Autoevaluacion {

    private String matriculaEstudiante;
    private List<Integer> calificaciones;
    private String comentario;

    public Autoevaluacion(String matriculaEstudiante, List<Integer> calificaciones,
            String comentario) {
        this.matriculaEstudiante = matriculaEstudiante;
        this.calificaciones = calificaciones;
        this.comentario = comentario;
    }

    public String getMatriculaEstudiante() {
        return matriculaEstudiante;
    }

    public void setMatriculaEstudiante(String matriculaEstudiante) {
        this.matriculaEstudiante = matriculaEstudiante;
    }

    public List<Integer> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Integer> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double calcularPromedio() {
        if (calificaciones == null || calificaciones.isEmpty()) {
            return 0;
        }
        int suma = 0;
        for (Integer calificacion : calificaciones) {
            suma += calificacion;
        }
        return (double) suma / calificaciones.size();
    }
}
