package sgpp.modelo.pojo;

public class ResponsableTecnico {

    private int identificador;
    private String nombre;
    private String departamento;
    private String puesto;
    private String correo;
    private int identificadorOrganizacionVinculada;

    public ResponsableTecnico() {
    }

    public ResponsableTecnico(int identificador, String nombre, String departamento,
            String puesto, String correo, int identificadorOrganizacionVinculada) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.departamento = departamento;
        this.puesto = puesto;
        this.correo = correo;
        this.identificadorOrganizacionVinculada = identificadorOrganizacionVinculada;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdentificadorOrganizacionVinculada() {
        return identificadorOrganizacionVinculada;
    }

    public void setIdentificadorOrganizacionVinculada(int identificadorOrganizacionVinculada) {
        this.identificadorOrganizacionVinculada = identificadorOrganizacionVinculada;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
