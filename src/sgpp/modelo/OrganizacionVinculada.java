package sgpp.modelo;

/**
 * Modelo de la tabla organizacion_vinculada.
 */
public class OrganizacionVinculada {

    private Integer idOrganizacionVinculada;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;

    public OrganizacionVinculada() {
    }

    public OrganizacionVinculada(Integer idOrganizacionVinculada, String nombre, String direccion,
            String telefono, String correo) {
        this.idOrganizacionVinculada = idOrganizacionVinculada;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Integer getIdOrganizacionVinculada() {
        return idOrganizacionVinculada;
    }

    public void setIdOrganizacionVinculada(Integer idOrganizacionVinculada) {
        this.idOrganizacionVinculada = idOrganizacionVinculada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
