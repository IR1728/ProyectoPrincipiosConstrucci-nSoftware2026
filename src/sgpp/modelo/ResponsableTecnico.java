package sgpp.modelo;

/**
 * Modelo de la tabla responsable_tecnico.
 */
public class ResponsableTecnico {

    private Integer idResponsable;
    private String nombre;
    private String puesto;
    private String departamento;
    private String telefono;
    private String correo;

    public ResponsableTecnico() {
    }

    public ResponsableTecnico(Integer idResponsable, String nombre, String puesto, String departamento,
            String telefono, String correo) {
        this.idResponsable = idResponsable;
        this.nombre = nombre;
        this.puesto = puesto;
        this.departamento = departamento;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Integer getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
