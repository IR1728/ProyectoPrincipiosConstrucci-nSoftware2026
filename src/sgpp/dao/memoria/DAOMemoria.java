package sgpp.dao.memoria;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sgpp.dao.AutoevaluacionDAO;
import sgpp.dao.EntregaDAO;
import sgpp.dao.EstudianteDAO;
import sgpp.dao.ExpedienteDAO;
import sgpp.dao.OrganizacionVinculadaDAO;
import sgpp.dao.ProyectoDAO;
import sgpp.dao.ReporteMensualDAO;
import sgpp.dao.ResponsableTecnicoDAO;
import sgpp.dao.UsuarioDAO;
import sgpp.modelo.Autoevaluacion;
import sgpp.modelo.Estudiante;
import sgpp.modelo.Expediente;
import sgpp.modelo.OrganizacionVinculada;
import sgpp.modelo.ProgramacionEntregas;
import sgpp.modelo.Proyecto;
import sgpp.modelo.ReporteMensual;
import sgpp.modelo.ResponsableTecnico;
import sgpp.modelo.Usuario;

public class DAOMemoria implements UsuarioDAO, OrganizacionVinculadaDAO, ResponsableTecnicoDAO,
        ProyectoDAO, EstudianteDAO, ExpedienteDAO, EntregaDAO, AutoevaluacionDAO, ReporteMensualDAO {

    private final List<Usuario> usuarios = new ArrayList<Usuario>();
    private final List<OrganizacionVinculada> organizaciones = new ArrayList<OrganizacionVinculada>();
    private final List<ResponsableTecnico> responsables = new ArrayList<ResponsableTecnico>();
    private final List<Proyecto> proyectos = new ArrayList<Proyecto>();
    private final List<Estudiante> estudiantes = new ArrayList<Estudiante>();
    private final List<Expediente> expedientes = new ArrayList<Expediente>();
    private final List<Autoevaluacion> autoevaluaciones = new ArrayList<Autoevaluacion>();
    private final List<ReporteMensual> reportes = new ArrayList<ReporteMensual>();
    private final Map<Integer, Integer> usuariosEstudiantes = new HashMap<Integer, Integer>();

    private ProgramacionEntregas programacionActiva;
    private int siguienteProyecto = 3;
    private int siguienteExpediente = 1;
    private int siguienteAutoevaluacion = 1;
    private final Integer idPeriodoActivo = 1;

    public DAOMemoria() {
        usuarios.add(new Usuario(1, "coordinador", "1234", "coord@uv.mx", "COORDINADOR", Boolean.TRUE));
        usuarios.add(new Usuario(2, "estudiante", "1234", "estudiante@uv.mx", "ESTUDIANTE", Boolean.TRUE));
        usuarios.add(new Usuario(3, "profesor", "1234", "profesor@uv.mx", "PROFESOR", Boolean.TRUE));

        organizaciones.add(new OrganizacionVinculada(1, "Dirección de Tecnologías UV",
                "Xalapa, Veracruz", "2280000001", "tecnologias@uv.mx"));
        organizaciones.add(new OrganizacionVinculada(2, "Secretaría Académica",
                "Xalapa, Veracruz", "2280000002", "academica@uv.mx"));

        responsables.add(new ResponsableTecnico(1, "María López", "Jefa de Área",
                "Sistemas", "2281111111", "maria.lopez@uv.mx"));
        responsables.add(new ResponsableTecnico(2, "Carlos Pérez", "Responsable Técnico",
                "Innovación", "2282222222", "carlos.perez@uv.mx"));

        proyectos.add(new Proyecto(1, "Automatización de reportes", "Reducir trabajo manual",
                "Scrum", 2, LocalDate.now(), LocalDate.now().plusMonths(4), 1, 1));
        proyectos.add(new Proyecto(2, "Portal de seguimiento", "Centralizar prácticas",
                "Kanban", 1, LocalDate.now(), LocalDate.now().plusMonths(4), 2, 2));

        estudiantes.add(new Estudiante(1, "S22000001", "Ana García", 8,
                new BigDecimal("9.40"), null, idPeriodoActivo));
        estudiantes.add(new Estudiante(2, "S22000002", "Luis Hernández", 7,
                new BigDecimal("8.70"), null, idPeriodoActivo));
        estudiantes.add(new Estudiante(3, "S22000003", "Sofía Martínez", 8,
                new BigDecimal("8.90"), 1, idPeriodoActivo));
        usuariosEstudiantes.put(2, 3);

        reportes.add(new ReporteMensual(1, LocalDateTime.now().minusDays(2), 1,
                "Actividades de levantamiento de requerimientos.", new byte[0], "Pendiente", 1, 3, idPeriodoActivo));
        reportes.add(new ReporteMensual(2, LocalDateTime.now().minusDays(1), 2,
                "Construcción de prototipo inicial.", new byte[0], "Pendiente", 1, 3, idPeriodoActivo));

        programacionActiva = new ProgramacionEntregas();
        programacionActiva.setAperturaDocumentosIniciales(LocalDate.now().minusDays(30));
        programacionActiva.setLimiteDocumentosIniciales(LocalDate.now().minusDays(15));
        programacionActiva.setAperturaReportes(LocalDate.now().minusDays(14));
        programacionActiva.setLimiteReportes(LocalDate.now().plusDays(10));
        programacionActiva.setAperturaDocumentosFinales(LocalDate.now().minusDays(1));
        programacionActiva.setLimiteDocumentosFinales(LocalDate.now().plusDays(20));
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return new ArrayList<Usuario>(usuarios);
    }

    @Override
    public Usuario buscarPorNombre(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public List<OrganizacionVinculada> listarActivas() {
        return new ArrayList<OrganizacionVinculada>(organizaciones);
    }

    @Override
    public List<ResponsableTecnico> listarResponsables() {
        return new ArrayList<ResponsableTecnico>(responsables);
    }

    @Override
    public Proyecto registrar(Proyecto proyecto) {
        proyecto.setIdProyecto(siguienteProyecto++);
        proyectos.add(proyecto);
        return proyecto;
    }

    @Override
    public List<Proyecto> listarProyectos() {
        return new ArrayList<Proyecto>(proyectos);
    }

    @Override
    public List<Proyecto> listarConCupo() {
        List<Proyecto> disponibles = new ArrayList<Proyecto>();
        for (Proyecto proyecto : proyectos) {
            if (contarEstudiantesAsignados(proyecto.getIdProyecto()) < proyecto.getMaxParticipantes()) {
                disponibles.add(proyecto);
            }
        }
        return disponibles;
    }

    @Override
    public int contarEstudiantesAsignados(Integer idProyecto) {
        int total = 0;
        for (Estudiante estudiante : estudiantes) {
            if (idProyecto != null && idProyecto.equals(estudiante.getIdProyecto())) {
                total++;
            }
        }
        return total;
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        return new ArrayList<Estudiante>(estudiantes);
    }

    @Override
    public List<Estudiante> listarPendientesPriorizados() {
        List<Estudiante> pendientes = new ArrayList<Estudiante>();
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getIdProyecto() == null) {
                pendientes.add(estudiante);
            }
        }
        Collections.sort(pendientes, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante uno, Estudiante dos) {
                int semestre = dos.getSemestre().compareTo(uno.getSemestre());
                if (semestre != 0) {
                    return semestre;
                }
                return dos.getPromedio().compareTo(uno.getPromedio());
            }
        });
        return pendientes;
    }

    @Override
    public Estudiante buscarPorUsuario(Integer idUsuario) {
        Integer idEstudiante = usuariosEstudiantes.get(idUsuario);
        return buscarPorId(idEstudiante);
    }

    @Override
    public Estudiante buscarPorId(Integer idEstudiante) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getIdEstudiante().equals(idEstudiante)) {
                return estudiante;
            }
        }
        return null;
    }

    @Override
    public void asignarProyecto(Integer idEstudiante, Integer idProyecto, Integer idPeriodo) {
        Estudiante estudiante = buscarPorId(idEstudiante);
        if (estudiante != null) {
            estudiante.setIdProyecto(idProyecto);
            estudiante.setIdPeriodo(idPeriodo);
        }
    }

    @Override
    public Expediente crearParaEstudiante(Integer idEstudiante, Integer idPeriodo) {
        Expediente expediente = new Expediente(siguienteExpediente++, new byte[0], "Abierto",
                idEstudiante, idPeriodo);
        expedientes.add(expediente);
        return expediente;
    }

    @Override
    public ProgramacionEntregas obtenerProgramacionActiva() {
        return programacionActiva;
    }

    @Override
    public void guardarProgramacion(ProgramacionEntregas programacion) {
        this.programacionActiva = programacion;
    }

    @Override
    public boolean existePeriodoActivo() {
        return idPeriodoActivo != null;
    }

    @Override
    public Integer getIdPeriodoActivo() {
        return idPeriodoActivo;
    }

    @Override
    public boolean existe(Integer idEstudiante, Integer idPeriodo) {
        for (Autoevaluacion autoevaluacion : autoevaluaciones) {
            if (autoevaluacion.getIdEstudiante().equals(idEstudiante)
                    && autoevaluacion.getIdPeriodo().equals(idPeriodo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Autoevaluacion registrar(Autoevaluacion autoevaluacion) {
        autoevaluacion.setIdAutoevaluacion(siguienteAutoevaluacion++);
        autoevaluaciones.add(autoevaluacion);
        return autoevaluacion;
    }

    @Override
    public List<ReporteMensual> listarPendientes() {
        List<ReporteMensual> pendientes = new ArrayList<ReporteMensual>();
        for (ReporteMensual reporte : reportes) {
            if ("Pendiente".equalsIgnoreCase(reporte.getEstado())) {
                pendientes.add(reporte);
            }
        }
        return pendientes;
    }

    @Override
    public void cambiarEstado(Integer idReporteMensual, String estado) {
        for (ReporteMensual reporte : reportes) {
            if (reporte.getIdReporteMensual().equals(idReporteMensual)) {
                reporte.setEstado(estado);
                return;
            }
        }
    }
}
