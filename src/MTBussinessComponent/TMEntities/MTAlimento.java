package MTBussinessComponent.TMEntities;

public class MTAlimento {
    private String mtNombre; // Caso C: Omnivoro
    private String mtGenoma; // X, XX, XY

    public MTAlimento(String mtNombre, String mtGenoma) {
        this.mtNombre = mtNombre;
        this.mtGenoma = mtGenoma;
    }

    public String getMtNombre() { return mtNombre; }
    public String getMtGenoma() { return mtGenoma; }
}