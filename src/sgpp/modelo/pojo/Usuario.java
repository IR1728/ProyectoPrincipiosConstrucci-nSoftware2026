package sgpp.modelo.pojo;

public class Usuario {

    private String numeroDePersonal;
    private String contrasena;
    private String nombreCompleto;
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String numeroDePersonal, String contrasena,
            String nombreCompleto, TipoUsuario tipoUsuario) {
        this.numeroDePersonal = numeroDePersonal;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNumeroDePersonal() {
        return numeroDePersonal;
    }

    public void setNumeroDePersonal(String numeroDePersonal) {
        this.numeroDePersonal = numeroDePersonal;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
