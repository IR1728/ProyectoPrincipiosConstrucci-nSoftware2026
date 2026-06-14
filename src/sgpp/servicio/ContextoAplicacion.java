package sgpp.servicio;

import sgpp.dao.memoria.DAOMemoria;

public final class ContextoAplicacion {

    private static final DAOMemoria DAO = new DAOMemoria();
    private static final SesionServicio SESION_SERVICIO = new SesionServicio(DAO);
    private static final ProyectoServicio PROYECTO_SERVICIO =
            new ProyectoServicio(SESION_SERVICIO, DAO, DAO, DAO);
    private static final AsignacionServicio ASIGNACION_SERVICIO =
            new AsignacionServicio(SESION_SERVICIO, DAO, DAO, DAO, DAO);
    private static final EntregaServicio ENTREGA_SERVICIO =
            new EntregaServicio(SESION_SERVICIO, DAO);
    private static final AutoevaluacionServicio AUTOEVALUACION_SERVICIO =
            new AutoevaluacionServicio(SESION_SERVICIO, DAO, DAO, DAO);
    private static final ReporteServicio REPORTE_SERVICIO =
            new ReporteServicio(SESION_SERVICIO, DAO, DAO);

    private ContextoAplicacion() {
    }

    public static SesionServicio getSesionServicio() {
        return SESION_SERVICIO;
    }

    public static ProyectoServicio getProyectoServicio() {
        return PROYECTO_SERVICIO;
    }

    public static AsignacionServicio getAsignacionServicio() {
        return ASIGNACION_SERVICIO;
    }

    public static EntregaServicio getEntregaServicio() {
        return ENTREGA_SERVICIO;
    }

    public static AutoevaluacionServicio getAutoevaluacionServicio() {
        return AUTOEVALUACION_SERVICIO;
    }

    public static ReporteServicio getReporteServicio() {
        return REPORTE_SERVICIO;
    }
}
