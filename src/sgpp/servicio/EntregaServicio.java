package sgpp.servicio;

import sgpp.dao.EntregaDAO;
import sgpp.modelo.ProgramacionEntregas;
import sgpp.modelo.RolUsuario;

public class EntregaServicio {

    private final SesionServicio sesionServicio;
    private final EntregaDAO entregaDAO;

    public EntregaServicio(SesionServicio sesionServicio, EntregaDAO entregaDAO) {
        this.sesionServicio = sesionServicio;
        this.entregaDAO = entregaDAO;
    }

    public ProgramacionEntregas obtenerProgramacion() {
        return entregaDAO.obtenerProgramacionActiva();
    }

    public void programarEntregas(ProgramacionEntregas programacion) {
        sesionServicio.exigirRol(RolUsuario.COORDINADOR);
        if (!entregaDAO.existePeriodoActivo()) {
            throw new IllegalStateException("Debe haber un periodo activo.");
        }
        ReglasNegocio.validarProgramacionEntregas(programacion);
        entregaDAO.guardarProgramacion(programacion);
    }
}
