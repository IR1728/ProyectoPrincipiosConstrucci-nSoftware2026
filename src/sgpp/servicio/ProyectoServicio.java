package sgpp.servicio;

import java.util.List;
import sgpp.dao.OrganizacionVinculadaDAO;
import sgpp.dao.ProyectoDAO;
import sgpp.dao.ResponsableTecnicoDAO;
import sgpp.modelo.OrganizacionVinculada;
import sgpp.modelo.Proyecto;
import sgpp.modelo.ResponsableTecnico;
import sgpp.modelo.RolUsuario;

public class ProyectoServicio {

    private final SesionServicio sesionServicio;
    private final ProyectoDAO proyectoDAO;
    private final OrganizacionVinculadaDAO organizacionDAO;
    private final ResponsableTecnicoDAO responsableDAO;

    public ProyectoServicio(SesionServicio sesionServicio, ProyectoDAO proyectoDAO,
            OrganizacionVinculadaDAO organizacionDAO, ResponsableTecnicoDAO responsableDAO) {
        this.sesionServicio = sesionServicio;
        this.proyectoDAO = proyectoDAO;
        this.organizacionDAO = organizacionDAO;
        this.responsableDAO = responsableDAO;
    }

    public Proyecto registrarProyecto(Proyecto proyecto) {
        sesionServicio.exigirRol(RolUsuario.COORDINADOR);
        if (organizacionDAO.listarActivas().isEmpty()) {
            throw new IllegalStateException("Debe existir al menos una organización vinculada activa.");
        }
        if (responsableDAO.listarResponsables().isEmpty()) {
            throw new IllegalStateException("Debe existir al menos un responsable técnico registrado.");
        }
        ReglasNegocio.validarTextoObligatorio(proyecto.getNombre(), "nombre");
        ReglasNegocio.validarTextoObligatorio(proyecto.getObjetivoGeneral(), "objetivo general");
        ReglasNegocio.validarTextoObligatorio(proyecto.getMetodologia(), "metodología");
        ReglasNegocio.validarNumeroPositivo(proyecto.getMaxParticipantes(), "máximo de participantes");
        ReglasNegocio.validarNumeroPositivo(proyecto.getIdOrganizacionVinculada(), "organización vinculada");
        ReglasNegocio.validarNumeroPositivo(proyecto.getIdResponsable(), "responsable técnico");
        ReglasNegocio.validarRango(proyecto.getFechaInicio(), proyecto.getFechaFin(), "proyecto");
        return proyectoDAO.registrar(proyecto);
    }

    public List<OrganizacionVinculada> listarOrganizaciones() {
        return organizacionDAO.listarActivas();
    }

    public List<ResponsableTecnico> listarResponsables() {
        return responsableDAO.listarResponsables();
    }

    public List<Proyecto> listarProyectosConCupo() {
        return proyectoDAO.listarConCupo();
    }
}
