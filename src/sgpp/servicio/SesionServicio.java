package sgpp.servicio;

import sgpp.dao.UsuarioDAO;
import sgpp.modelo.RolUsuario;
import sgpp.modelo.Usuario;

public class SesionServicio {

    private final UsuarioDAO usuarioDAO;
    private Usuario usuarioActual;

    public SesionServicio(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarioDAO.buscarPorNombre(nombreUsuario);
        if (usuario == null || !usuario.getContrasena().equals(contrasena)
                || !Boolean.TRUE.equals(usuario.getActivo())) {
            throw new IllegalArgumentException("Usuario o contraseña inválidos.");
        }
        usuarioActual = usuario;
        return usuarioActual;
    }

    public Usuario getUsuarioActual() {
        if (usuarioActual == null) {
            throw new SecurityException("Debe iniciar sesión.");
        }
        return usuarioActual;
    }

    public boolean tieneRol(RolUsuario rol) {
        return usuarioActual != null && rol.name().equalsIgnoreCase(usuarioActual.getRol());
    }

    public void exigirRol(RolUsuario rol) {
        if (!tieneRol(rol)) {
            throw new SecurityException("No tiene permiso para realizar esta acción.");
        }
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}
