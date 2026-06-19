package sgpp.modelo.pojo;

public class OrganizacionVinculada {

    private int identificador;
    private String nombre;
    private String sector;
    private String correo;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String estado;

    public OrganizacionVinculada() {
    }

    public OrganizacionVinculada(int identificador, String nombre, String sector,
            String correo, String telefono, String direccion, String ciudad,
            String estado) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.sector = sector;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
