package MTAppComponent.TMConsoleApp;

import java.util.logging.Logger;

public class MTKGD {
    private static final Logger LOGGER = Logger.getLogger(MTKGD.class.getName());
    private String mtcedula;
    private String mtnombre;
    
    public MTKGD(String cedula, String nombre) {
        this.mtcedula = cedula;
        this.mtnombre = nombre;
    }
    public String getCedula() {
        return mtcedula;
    }
    public void setCedula(String cedula) {
        this.mtcedula = cedula;
    }
    public String getNombre() {
        return mtnombre;
    }
    public void setNombre(String nombre) {
        this.mtnombre = nombre;
    }
    
    public void show(){
        LOGGER.info("[+] Alumno:");
        LOGGER.info(getCedula() + " | " + getNombre());
    }

    @Override
    public String toString(){
        return getClass().getName()
        + "\n cedula    : "+ getCedula()
        + "\n nombre    : "+ getNombre();
    }
}
