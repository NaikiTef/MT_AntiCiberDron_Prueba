package MTBussinessComponent.TMEntities;

public class MTAlimento {
    private String mtNombre;      // Ejemplo: "Nectarivoro", "Carnivoro"
    private String mtTipo;        // Clase de alimento (Asexual, macho, hembra)
    private String mtGenoma;      // El genoma inyectado (X, XX, XY) o null

    // Constructor básico
    public MTAlimento(String mtNombre) {
        this.mtNombre = mtNombre;
        this.mtGenoma = "";
    }

    // --- Getters y Setters ---

    public String getMtNombre() {
        return mtNombre;
    }

    public void setMtNombre(String mtNombre) {
        this.mtNombre = mtNombre;
    }

    public String getMtGenoma() {
        return mtGenoma;
    }

    public void setMtGenoma(String mtGenoma) {
        this.mtGenoma = mtGenoma;
    }

    public String getMtTipo() {
        return mtTipo;
    }

    public void setMtTipo(String mtTipo) {
        this.mtTipo = mtTipo;
    }
}
