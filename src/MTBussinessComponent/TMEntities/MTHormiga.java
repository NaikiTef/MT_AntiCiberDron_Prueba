package MTBussinessComponent.TMEntities;

public class MTHormiga {
    private String mtTipo;           // HLarva, HZangano, etc.
    private String mtSexo;           // X, XX, XY
    private String mtEstado;         // VIVA, MUERTA
    private String mtSuperHabilidad; // Caso C: superRastreo(...)

    public MTHormiga(String mtTipo, String mtSexo) {
        this.mtTipo = mtTipo;
        this.mtSexo = mtSexo;
        this.mtEstado = "VIVA";
        this.mtSuperHabilidad = "Ninguna";
    }

    // Getters y Setters con estándar mt
    public String getMtTipo() { return mtTipo; }
    public void setMtTipo(String mtTipo) { this.mtTipo = mtTipo; }
    public String getMtSexo() { return mtSexo; }
    public void setMtSexo(String mtSexo) { this.mtSexo = mtSexo; }
    public String getMtEstado() { return mtEstado; }
    public void setMtEstado(String mtEstado) { this.mtEstado = mtEstado; }
    public void setMtSuperHabilidad(String mtSuperHabilidad) { this.mtSuperHabilidad = mtSuperHabilidad; }

    public void mtMostrarInfo() {
        System.out.println("Hormiga: " + mtTipo + " | Sexo: " + mtSexo + " | Estado: " + mtEstado + " | Habilidad: " + mtSuperHabilidad);
    }
}