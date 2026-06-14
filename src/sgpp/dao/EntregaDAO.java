package sgpp.dao;

import sgpp.modelo.ProgramacionEntregas;

public interface EntregaDAO {
    ProgramacionEntregas obtenerProgramacionActiva();

    void guardarProgramacion(ProgramacionEntregas programacion);

    boolean existePeriodoActivo();

    Integer getIdPeriodoActivo();
}
