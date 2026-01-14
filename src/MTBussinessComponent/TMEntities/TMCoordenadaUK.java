package MTBussinessComponent.TMEntities;

public class TMCoordenadaUK {
    private String mtGeoposicion;
    private String mtTipoArsenal;
    private String mtAccion;

    public TMCoordenadaUK() {
    }

    public TMCoordenadaUK(String geoposicion, String tipoArsenal, String acccion) {
        this.mtGeoposicion = geoposicion;
        this.mtTipoArsenal = tipoArsenal;
        this.mtAccion = acccion;
    }

    public String getGeoposicion() {
        return mtGeoposicion;
    }
    public void setGeoposicion(String geoposicion) {
        this.mtGeoposicion = geoposicion;
    }
    public String getTipoArsenal() {
        return mtTipoArsenal;
    }
    public void setTipoArsenal(String tipoArsenal) {
        this.mtTipoArsenal = tipoArsenal;
    }
    public String getAcccion() {
        return mtAccion;
    }
    public void setAcccion(String acccion) {
        this.mtAccion = acccion;
    }

    @Override
    public String toString(){
        return getClass().getName()
        + "\n Geoposicion    : "+ getGeoposicion()
        + "\n TipoArsenal    : "+ getTipoArsenal()
        + "\n Accion         : "+ getAcccion();
    }

}
