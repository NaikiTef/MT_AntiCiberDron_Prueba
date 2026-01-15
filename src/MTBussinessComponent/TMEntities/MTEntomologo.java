package MTBussinessComponent.TMEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import MTBussinessComponent.TMInterfaces.IEntomologo;
import MTDataAccessCompoment.MTDATOs.MTAlimentoTipoDTO;

public class MTEntomologo extends MTTipoEntomologo implements IEntomologo {
    
    // Constantes para colores ANSI y evitar errores de escape
    private static final String MT_ANSI_RESET = "\u001B[0m";
    private static final String MT_ANSI_BLUE  = "\u001B[34m";

    public MTEntomologo(MTAlimentoTipoDTO mtAlimentoTipo) {
        super(mtAlimentoTipo);
    }

    @Override
    public List<MTHormiga> mtEntAntNest() {
        System.out.println("[+] Extrayendo hormigas del Storage...");
        // Simulación de retorno de lista para la interfaz
        return new ArrayList<>(); 
    }

    @Override
    public List<MTAlimento> mtEntAntFood() {
        System.out.println("[+] Extrayendo alimentos del Storage...");
        // Simulación de retorno de lista para la interfaz
        return new ArrayList<>();
    }
    
    @Override
    public MTHormiga mtAlimentarAnt(MTHormiga mtHormiga, MTAlimento mtAlimento) {
        System.out.println("[!] Procesando alimentación para Caso C...");
        
        // REQUERIMIENTO: La hormiga VIVE si come su alimento (Caso C: Omnivoro)
        if (mtAlimento.getMtNombre().equalsIgnoreCase("Omnivoro")) {
            System.out.println(MT_ANSI_BLUE + "La hormiga ha comido con éxito." + MT_ANSI_RESET);
            
            // REQUERIMIENTO 3: Transformación de HLarva
            if (mtHormiga.getMtTipo().equals("HLarva")) {
                mtHormiga.setMtTipo("HZangano"); // Evoluciona a tipo del Caso C
                mtHormiga.setMtSuperHabilidad("superRastreo(...)"); // Habilidad del Caso C
                System.out.println("[+] Evolución: HLarva -> HZángano con superRastreo.");
            }
        } else {
            // Caso contrario MUERE
            System.err.println("Alimento incompatible. La hormiga MUERE.");
            mtHormiga.setMtEstado("MUERTA");
        }
        
        return mtHormiga;
    }

    // REQUERIMIENTO 2: Proceso ETL
    public void Cosechar() {
        System.out.println("[+] Iniciando Cosecha (Proceso ETL) - Caso C");
        
        // Uso de "/" para evitar errores de secuencia de escape en Windows
        List<String> mtLineasAlimento = mtLeerArchivo("MTStorage/MTDataFiles/AntFood.txt");
        for (String mtLinea : mtLineasAlimento) {
            mtProcesarDato(mtLinea, "Alimento");
        }

        List<String> mtLineasHormigas = mtLeerArchivo("MTStorage/MTDataFiles/AntNest.txt");
        for (String mtLinea : mtLineasHormigas) {
            mtProcesarDato(mtLinea, "Hormiga");
        }
    }

    private void mtProcesarDato(String mtDato, String mtTipo) {
        // Mostrar Loading/Waiting por cada dato
        System.out.print("Loading " + mtTipo + " [" + mtDato + "] ");
        
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(300); 
                System.out.print(".");
            }
            
            // Validación: Solo datos del Caso C se muestran en AZUL
            if (mtEsDatoValido(mtDato)) {
                System.out.println(MT_ANSI_BLUE + " [OK - Guardado]" + MT_ANSI_RESET);
            } else {
                // Datos con error se muestran en ROJO
                System.err.println(" [ERROR - No corresponde al caso]");
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean mtEsDatoValido(String mtDato) {
        // Filtro para Caso C: HZángano y Omnívoro
        return mtDato.equalsIgnoreCase("HZangano") || mtDato.equalsIgnoreCase("Omnivoro"); 
    }

    private List<String> mtLeerArchivo(String mtPath) {
        // Datos de prueba que incluyen elementos de tu Caso C
        return Arrays.asList("HZangano", "Omnivoro", "HLarva", "Carnivoro", "HSoldado");
    }
}