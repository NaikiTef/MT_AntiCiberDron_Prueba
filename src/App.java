import java.util.logging.Logger;
import MTDataAccessCompoment.MTDAOs.MTHormigaDAO;
import MTDataAccessCompoment.MTDATOs.MTVWHormigaDTO;

import MTAppComponent.TMConsoleApp.TMSistemRuso;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    public static void main(String[] args) throws Exception{
    // Llamada a iniciarSesion del sistema ruso
    TMSistemRuso sistemaRuso = new TMSistemRuso();
    sistemaRuso.iniciarSesion();
    }

}

