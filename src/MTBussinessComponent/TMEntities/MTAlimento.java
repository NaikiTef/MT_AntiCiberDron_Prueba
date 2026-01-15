package MTBussinessComponent.TMEntities;

public class MTAlimento {
    private String mtNombre;        // Nombre del alimento (ej. "Omnivoro" para Caso C)
    private String mtGenoma;        // Genoma inyectado (X, XX, XY)
    private boolean mtEstaPreparado; // Estado de preparación del alimento

    public MTAlimento(String mtNombre) {
        this.mtNombre = mtNombre;
        this.mtEstaPreparado = false; // Inicia sin preparar
        this.mtGenoma = "";           // Sin genoma hasta que el genetista lo inyecte
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

    /**
     * Permite al Entomólogo Genetista inyectar el genoma.
     */
    public void setMtGenoma(String mtGenoma) {
        this.mtGenoma = mtGenoma;
    }

    public boolean isMtEstaPreparado() {
        return mtEstaPreparado;
    }

    public void setMtEstaPreparado(boolean mtEstaPreparado) {
        this.mtEstaPreparado = mtEstaPreparado;
    }

    /**
     * Retorna el tipo de alimento formateado para comparaciones.
     */
    public String getMtTipo() {
        return this.mtNombre;
    }
}