package sgpp.modelo.pojo;

public enum EstadoReporte {
    PENDIENTE("Pendiente"),
    ACEPTADO("Aceptado"),
    RECHAZADO("Rechazado");

    private final String etiqueta;

    EstadoReporte(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
