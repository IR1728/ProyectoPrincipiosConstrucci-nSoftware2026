package sgpp.modelo.pojo;

public class Estudiante {

    private String matricula;
    private String nombre;
    private String correo;
    private String telefono;
    private int semestre;
    private double promedio;
    private int identificadorProyectoAsignado;

    public static final int SIN_PROYECTO = 0;

    public Estudiante() {
    }

    public Estudiante(String matricula, String nombre, String correo, String telefono,
            int semestre, double promedio, int identificadorProyectoAsignado) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.semestre = semestre;
        this.promedio = promedio;
        this.identificadorProyectoAsignado = identificadorProyectoAsignado;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public int getIdentificadorProyectoAsignado() {
        return identificadorProyectoAsignado;
    }

    public void setIdentificadorProyectoAsignado(int identificadorProyectoAsignado) {
        this.identificadorProyectoAsignado = identificadorProyectoAsignado;
    }

    public boolean tieneProyectoAsignado() {
        return identificadorProyectoAsignado != SIN_PROYECTO;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
