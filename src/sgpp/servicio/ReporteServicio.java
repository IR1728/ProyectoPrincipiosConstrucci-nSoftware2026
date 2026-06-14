package sgpp.servicio;

import java.util.List;
import sgpp.dao.EntregaDAO;
import sgpp.dao.ReporteMensualDAO;
import sgpp.modelo.ReporteMensual;
import sgpp.modelo.RolUsuario;

public class ReporteServicio {

    private final SesionServicio sesionServicio;
    private final ReporteMensualDAO reporteDAO;
    private final EntregaDAO entregaDAO;

    public ReporteServicio(SesionServicio sesionServicio, ReporteMensualDAO reporteDAO, EntregaDAO entregaDAO) {
        this.sesionServicio = sesionServicio;
        this.reporteDAO = reporteDAO;
        this.entregaDAO = entregaDAO;
    }

    public List<ReporteMensual> listarReportesPendientes() {
        sesionServicio.exigirRol(RolUsuario.PROFESOR);
        if (!entregaDAO.existePeriodoActivo()) {
            throw new IllegalStateException("Debe haber un periodo activo.");
        }
        return reporteDAO.listarPendientes();
    }

    public void aceptar(ReporteMensual reporte) {
        cambiarEstado(reporte, "Aceptado");
    }

    public void rechazar(ReporteMensual reporte) {
        cambiarEstado(reporte, "Rechazado");
    }

    private void cambiarEstado(ReporteMensual reporte, String estado) {
        sesionServicio.exigirRol(RolUsuario.PROFESOR);
        if (reporte == null) {
            throw new IllegalArgumentException("Seleccione un reporte.");
        }
        reporteDAO.cambiarEstado(reporte.getIdReporteMensual(), estado);
    }
}
