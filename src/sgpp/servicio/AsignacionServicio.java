package sgpp.servicio;

import java.util.List;
import sgpp.dao.EntregaDAO;
import sgpp.dao.EstudianteDAO;
import sgpp.dao.ExpedienteDAO;
import sgpp.dao.ProyectoDAO;
import sgpp.modelo.Estudiante;
import sgpp.modelo.Proyecto;
import sgpp.modelo.RolUsuario;

public class AsignacionServicio {

    private final SesionServicio sesionServicio;
    private final EstudianteDAO estudianteDAO;
    private final ProyectoDAO proyectoDAO;
    private final ExpedienteDAO expedienteDAO;
    private final EntregaDAO entregaDAO;

    public AsignacionServicio(SesionServicio sesionServicio, EstudianteDAO estudianteDAO,
            ProyectoDAO proyectoDAO, ExpedienteDAO expedienteDAO, EntregaDAO entregaDAO) {
        this.sesionServicio = sesionServicio;
        this.estudianteDAO = estudianteDAO;
        this.proyectoDAO = proyectoDAO;
        this.expedienteDAO = expedienteDAO;
        this.entregaDAO = entregaDAO;
    }

    public List<Estudiante> listarEstudiantesPendientes() {
        sesionServicio.exigirRol(RolUsuario.COORDINADOR);
        return estudianteDAO.listarPendientesPriorizados();
    }

    public List<Proyecto> listarProyectosConCupo() {
        sesionServicio.exigirRol(RolUsuario.COORDINADOR);
        return proyectoDAO.listarConCupo();
    }

    public void asignarProyecto(Estudiante estudiante, Proyecto proyecto) {
        sesionServicio.exigirRol(RolUsuario.COORDINADOR);
        if (!entregaDAO.existePeriodoActivo()) {
            throw new IllegalStateException("Debe haber un periodo escolar activo.");
        }
        if (estudiante == null) {
            throw new IllegalArgumentException("Seleccione un estudiante.");
        }
        if (proyecto == null) {
            throw new IllegalArgumentException("Seleccione un proyecto.");
        }
        if (estudiante.getIdProyecto() != null) {
            throw new IllegalStateException("El estudiante ya tiene un proyecto asignado.");
        }
        if (proyectoDAO.contarEstudiantesAsignados(proyecto.getIdProyecto()) >= proyecto.getMaxParticipantes()) {
            throw new IllegalStateException("El proyecto no tiene cupo disponible.");
        }
        Integer idPeriodo = entregaDAO.getIdPeriodoActivo();
        estudianteDAO.asignarProyecto(estudiante.getIdEstudiante(), proyecto.getIdProyecto(), idPeriodo);
        expedienteDAO.crearParaEstudiante(estudiante.getIdEstudiante(), idPeriodo);
    }
}
