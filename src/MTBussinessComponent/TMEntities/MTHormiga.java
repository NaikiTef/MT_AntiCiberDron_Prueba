package MTBussinessComponent.TMEntities;

import MTDataAccessCompoment.MTDATOs.MTVWHormigaDTO;
import MTBussinessComponent.TMInterfaces.MTFactoryBL;
import MTDataAccessCompoment.MTDAOs.MTHormigaDAO;

public class MTHormiga {
    // Atributos según el requerimiento y diagrama
    private String mtTipo;           // HLarva, HSoldado, etc.
    private String mtGenoma;           // X, XX, XY
    private String mtEstado;         // VIVA, MUERTA
    private String mtTipoAlimento;   // El tipo de alimento que puede comer
    private String mtSuperHabilidad; // La habilidad ganada al evolucionar
    

    public MTHormiga(String mtTipo, String mtGenoma, String mtTipoAlimento) {
        this.mtTipo = mtTipo;
        this.mtGenoma = mtGenoma;
        this.mtTipoAlimento = mtTipoAlimento;
        this.mtEstado = "VIVA"; // Por defecto nacen vivas
        this.mtSuperHabilidad = "Ninguna";
    }

    // --- Getters y Setters con el prefijo 'mt' ---

    public String getMtTipo() {
        return mtTipo;
    }

    public void setMtTipo(String mtTipo) {
        this.mtTipo = mtTipo;
    }

    public String getmtGenoma() {
        return mtGenoma;
    }

    public void setmtGenoma(String mtGenoma) {
        this.mtGenoma = mtGenoma;
    }

    public String getMtEstado() {
        return mtEstado;
    }

    public void setMtEstado(String mtEstado) {
        this.mtEstado = mtEstado;
    }

    public String getMtTipoAlimento() {
        return mtTipoAlimento;
    }

    public String getMtSuperHabilidad() {
        return mtSuperHabilidad;
    }

    public void setMtSuperHabilidad(String mtSuperHabilidad) {
        this.mtSuperHabilidad = mtSuperHabilidad;
    }

    public void setMtGenoma(String mtGenoma) {
        this.mtGenoma = mtGenoma;
    }

    /**
     * Muestra la información de la hormiga en consola (usado en el ETL)
     */
    public void mtMostrarInfo() {
        System.out.println("Hormiga: " + mtTipo + " | Sexo: " + mtGenoma + " | Habilidad: " + mtSuperHabilidad);
    }
}
