package sgpp.dao;

import sgpp.modelo.Autoevaluacion;

public interface AutoevaluacionDAO {
    boolean existe(Integer idEstudiante, Integer idPeriodo);

    Autoevaluacion registrar(Autoevaluacion autoevaluacion);
}
