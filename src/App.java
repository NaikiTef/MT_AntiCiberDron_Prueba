import java.util.Scanner;
import java.util.logging.Logger;
import MTDataAccessCompoment.MTDAOs.MTHormigaDAO;
import MTDataAccessCompoment.MTDATOs.MTAlimentoTipoDTO;
import MTDataAccessCompoment.MTDATOs.MTVWHormigaDTO;

import MTAppComponent.TMConsoleApp.TMSistemRuso;
import MTBussinessComponent.TMEntities.MTEntomologo;

public class App {
    
    public static void main(String[] args) throws Exception{
    //private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    // TMSistemRuso sistemaRuso = new TMSistemRuso();
    // sistemaRuso.mtautenticacion();
    


    Scanner mtScanner = new Scanner(System.in);
        
        // 1. REQUERIMIENTO: Autenticación (patmic, 123) con máx 3 intentos
        boolean mtAutenticado = false;
        int mtIntentos = 0;
        
        System.out.println("--- SISTEMA ANTDRON2K25 ---");
        while (mtIntentos < 3 && !mtAutenticado) {
            System.out.print("Usuario: ");
            String mtUser = mtScanner.nextLine();
            System.out.print("Contraseña: ");
            String mtPass = mtScanner.nextLine();
            
            if (mtUser.equals("patmic") && mtPass.equals("123")) {
                mtAutenticado = true;
                // REQUERIMIENTO: Presentar cédula y nombre completo tras éxito
                System.out.println("\nBienvenido: [TU NOMBRE] - Cédula: [TU CÉDULA]");
            } else {
                mtIntentos++;
                System.out.println("Error. Intentos restantes: " + (3 - mtIntentos));
            }
        }

        if (mtAutenticado) {
            // 2. Probar el Entomólogo
            // Creamos un DTO ficticio para inicializarlo
            MTAlimentoTipoDTO mtDto = new MTAlimentoTipoDTO(); 
            MTEntomologo mtEntomologo = new MTEntomologo(mtDto);
            
            // Ejecutamos la Cosecha (Proceso ETL con colores)
            mtEntomologo.Cosechar();
            
            System.out.println("\n--- Pruebas finalizadas con éxito ---");
        } else {
            System.out.println("Acceso denegado. El sistema se cerrará.");
        }
        
        mtScanner.close();
    }

}

