package sgpp.dao;

import java.util.List;
import sgpp.modelo.Usuario;

public interface UsuarioDAO {
    List<Usuario> listarUsuarios();

    Usuario buscarPorNombre(String nombreUsuario);
}
