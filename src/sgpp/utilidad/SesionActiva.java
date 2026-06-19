package sgpp.utilidad;

import sgpp.modelo.pojo.Usuario;

public final class SesionActiva {

    private static Usuario usuarioActual;

    private SesionActiva() {
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }
}
