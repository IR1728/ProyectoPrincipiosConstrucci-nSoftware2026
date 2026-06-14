package sgpp.dao;

import java.util.List;
import sgpp.modelo.Estudiante;

public interface EstudianteDAO {
    List<Estudiante> listarEstudiantes();

    List<Estudiante> listarPendientesPriorizados();

    Estudiante buscarPorUsuario(Integer idUsuario);

    Estudiante buscarPorId(Integer idEstudiante);

    void asignarProyecto(Integer idEstudiante, Integer idProyecto, Integer idPeriodo);
}
