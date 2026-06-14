package sgpp.modelo;

public enum RolUsuario {
    COORDINADOR,
    ESTUDIANTE,
    PROFESOR;

    public static RolUsuario desdeTexto(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("El rol es obligatorio.");
        }
        return RolUsuario.valueOf(valor.trim().toUpperCase());
    }
}
