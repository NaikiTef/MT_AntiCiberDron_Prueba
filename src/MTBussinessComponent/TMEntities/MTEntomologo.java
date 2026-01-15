package MTBussinessComponent.TMEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import MTBussinessComponent.TMInterfaces.IEntomologo;
import MTDataAccessCompoment.MTDATOs.MTAlimentoTipoDTO;

public class MTEntomologo extends MTTipoEntomologo implements IEntomologo {
    
    // Constantes para colores ANSI según requerimiento de visualización
    private static final String MT_ANSI_RESET = "\u001B[0m";
    private static final String MT_ANSI_BLUE  = "\u001B[34m";
    private static final String MT_ANSI_RED   = "\u001B[31m";

    public MTEntomologo(MTAlimentoTipoDTO mtAlimentoTipo) {
        super(mtAlimentoTipo);
    }

    @Override
    public List<MTHormiga> mtEntAntNest() {
        return new ArrayList<>(); 
    }

    @Override
    public List<MTAlimento> mtEntAntFood() {
        return new ArrayList<>();
    }
    
    /**
     * Requerimiento 3: La hormiga VIVE si come su alimento (Caso C: Omnivoro).
     * HLarva se transforma y cambia de sexo acorde al genoma.
     */
    @Override
    public MTHormiga mtAlimentarAnt(MTHormiga mtHormiga, MTAlimento mtAlimento) {
        if (mtAlimento.getMtNombre().equalsIgnoreCase("Omnivoro")) {
            System.out.println(MT_ANSI_BLUE + "La hormiga ha comido con éxito." + MT_ANSI_RESET);
            
            // Transformación de HLarva según genoma del alimento
            if (mtHormiga.getMtTipo().equals("HLarva")) {
                mtHormiga.setMtTipo("HZangano"); 
                mtHormiga.setMtSexo(mtAlimento.getMtGenoma()); // Cambia sexo según genoma
                mtHormiga.setMtSuperHabilidad("superRastreo(...)"); // Habilidad Caso C
                System.out.println("[+] Evolución: HLarva -> HZángano con superRastreo.");
            }
        } else {
            // Caso contrario MUERE y el alimento se elimina
            System.err.println("Alimento incompatible. La hormiga MUERE.");
            mtHormiga.setMtEstado("MUERTA");
        }
        return mtHormiga;
    }

    /**
     * Requerimiento: Preparar alimento sin genoma.
     */
    public MTAlimento mtPrepararAlimento(MTAlimento mtAlimento) {
        mtAlimento.setMtEstaPreparado(true);
        System.out.println("[ Preparado ]—( " + mtAlimento.getMtNombre() + " )—");
        return mtAlimento;
    }

    /**
     * Requerimiento 2: Proceso ETL con Loading visual y colores.
     */
    public void Cosechar() {
        System.out.println("[+] Iniciando Cosecha (Proceso ETL) - Caso C");
        
        // Uso de "/" en las rutas para evitar error "Invalid escape sequence"
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
        // Iconos según imagen: .o. para Alimento y \|/- para Hormiga
        String mtIcono = mtTipo.equalsIgnoreCase("Alimento") ? ".o." : "\\|/-";
        
        // Formato solicitado: Alimento [Omnivoro] .o. .o. .o. [OK - Guardado]
        System.out.print(mtTipo + " [" + mtDato + "] ");
        
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(300); 
                System.out.print(mtIcono + " "); 
            }
            
            // Validación específica para Caso C
            if (mtEsDatoValido(mtDato)) {
                System.out.println(MT_ANSI_BLUE + "[OK - Guardado]" + MT_ANSI_RESET);
            } else {
                System.out.println(MT_ANSI_RED + "[ERROR - No corresponde]" + MT_ANSI_RESET);
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean mtEsDatoValido(String mtDato) {
        return mtDato.equalsIgnoreCase("HZangano") || mtDato.equalsIgnoreCase("Omnivoro"); 
    }

    private List<String> mtLeerArchivo(String mtPath) {
        // Datos de simulación para pruebas del Caso C
        return Arrays.asList("HZangano", "Omnivoro", "HLarva", "Carnivoro");
    }
}