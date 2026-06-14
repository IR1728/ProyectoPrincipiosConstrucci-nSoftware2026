package sgpp.dao;

import sgpp.modelo.Expediente;

public interface ExpedienteDAO {
    Expediente crearParaEstudiante(Integer idEstudiante, Integer idPeriodo);
}
