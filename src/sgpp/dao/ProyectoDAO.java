package sgpp.dao;

import java.util.List;
import sgpp.modelo.Proyecto;

public interface ProyectoDAO {
    Proyecto registrar(Proyecto proyecto);

    List<Proyecto> listarProyectos();

    List<Proyecto> listarConCupo();

    int contarEstudiantesAsignados(Integer idProyecto);
}
