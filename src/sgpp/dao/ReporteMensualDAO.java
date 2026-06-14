package sgpp.dao;

import java.util.List;
import sgpp.modelo.ReporteMensual;

public interface ReporteMensualDAO {
    List<ReporteMensual> listarPendientes();

    void cambiarEstado(Integer idReporteMensual, String estado);
}
