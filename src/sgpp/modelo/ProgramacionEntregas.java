package sgpp.modelo;

import java.time.LocalDate;

public class ProgramacionEntregas {

    private LocalDate aperturaDocumentosIniciales;
    private LocalDate limiteDocumentosIniciales;
    private LocalDate aperturaReportes;
    private LocalDate limiteReportes;
    private LocalDate aperturaDocumentosFinales;
    private LocalDate limiteDocumentosFinales;

    public LocalDate getAperturaDocumentosIniciales() {
        return aperturaDocumentosIniciales;
    }

    public void setAperturaDocumentosIniciales(LocalDate aperturaDocumentosIniciales) {
        this.aperturaDocumentosIniciales = aperturaDocumentosIniciales;
    }

    public LocalDate getLimiteDocumentosIniciales() {
        return limiteDocumentosIniciales;
    }

    public void setLimiteDocumentosIniciales(LocalDate limiteDocumentosIniciales) {
        this.limiteDocumentosIniciales = limiteDocumentosIniciales;
    }

    public LocalDate getAperturaReportes() {
        return aperturaReportes;
    }

    public void setAperturaReportes(LocalDate aperturaReportes) {
        this.aperturaReportes = aperturaReportes;
    }

    public LocalDate getLimiteReportes() {
        return limiteReportes;
    }

    public void setLimiteReportes(LocalDate limiteReportes) {
        this.limiteReportes = limiteReportes;
    }

    public LocalDate getAperturaDocumentosFinales() {
        return aperturaDocumentosFinales;
    }

    public void setAperturaDocumentosFinales(LocalDate aperturaDocumentosFinales) {
        this.aperturaDocumentosFinales = aperturaDocumentosFinales;
    }

    public LocalDate getLimiteDocumentosFinales() {
        return limiteDocumentosFinales;
    }

    public void setLimiteDocumentosFinales(LocalDate limiteDocumentosFinales) {
        this.limiteDocumentosFinales = limiteDocumentosFinales;
    }
}
