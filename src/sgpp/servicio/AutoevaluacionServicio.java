package sgpp.servicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import sgpp.dao.AutoevaluacionDAO;
import sgpp.dao.EntregaDAO;
import sgpp.dao.EstudianteDAO;
import sgpp.modelo.Autoevaluacion;
import sgpp.modelo.Estudiante;
import sgpp.modelo.RolUsuario;
import sgpp.modelo.Usuario;

public class AutoevaluacionServicio {

    private final SesionServicio sesionServicio;
    private final EstudianteDAO estudianteDAO;
    private final EntregaDAO entregaDAO;
    private final AutoevaluacionDAO autoevaluacionDAO;

    public AutoevaluacionServicio(SesionServicio sesionServicio, EstudianteDAO estudianteDAO,
            EntregaDAO entregaDAO, AutoevaluacionDAO autoevaluacionDAO) {
        this.sesionServicio = sesionServicio;
        this.estudianteDAO = estudianteDAO;
        this.entregaDAO = entregaDAO;
        this.autoevaluacionDAO = autoevaluacionDAO;
    }

    public Autoevaluacion registrarAutoevaluacion(Integer puntaje, String comentarios) {
        sesionServicio.exigirRol(RolUsuario.ESTUDIANTE);
        Usuario usuario = sesionServicio.getUsuarioActual();
        Estudiante estudiante = estudianteDAO.buscarPorUsuario(usuario.getIdUsuario());
        if (estudiante == null || estudiante.getIdProyecto() == null) {
            throw new IllegalStateException("El estudiante debe estar asignado a un proyecto.");
        }
        Integer idPeriodo = entregaDAO.getIdPeriodoActivo();
        ReglasNegocio.validarFechaDentroDeDocumentosFinales(entregaDAO.obtenerProgramacionActiva(), LocalDate.now());
        if (autoevaluacionDAO.existe(estudiante.getIdEstudiante(), idPeriodo)) {
            throw new IllegalStateException("Ya existe una autoevaluación registrada.");
        }
        ReglasNegocio.validarNumeroPositivo(puntaje, "puntaje");
        if (puntaje > 100) {
            throw new IllegalArgumentException("El puntaje no puede ser mayor a 100.");
        }
        Autoevaluacion autoevaluacion = new Autoevaluacion(null, estudiante.getIdEstudiante(), idPeriodo,
                puntaje, comentarios, LocalDateTime.now());
        return autoevaluacionDAO.registrar(autoevaluacion);
    }
}
