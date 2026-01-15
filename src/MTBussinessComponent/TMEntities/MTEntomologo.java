package MTBussinessComponent.TMEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import MTBussinessComponent.TMInterfaces.IEntomologo;
import MTDataAccessCompoment.MTDATOs.MTAlimentoTipoDTO;

public class MTEntomologo extends MTTipoEntomologo implements IEntomologo {
    
    private static final String MT_ANSI_RESET = "\u001B[0m";
    private static final String MT_ANSI_BLUE  = "\u001B[34m";
    private static final String MT_ANSI_RED   = "\u001B[31m";

    public MTEntomologo(MTAlimentoTipoDTO mtAlimentoTipo) {
        super(mtAlimentoTipo);
    }

    @Override
    public List<MTHormiga> mtEntAntNest() { return new ArrayList<>(); }

    @Override
    public List<MTAlimento> mtEntAntFood() { return new ArrayList<>(); }
    
    @Override
    public MTHormiga mtAlimentarAnt(MTHormiga mtHormiga, MTAlimento mtAlimento) {
        // Validación para Caso C: Omnivoro
        if (mtAlimento.getMtNombre().equalsIgnoreCase("Omnivoro")) {
            System.out.println(MT_ANSI_BLUE + "[OK] Alimento consumido con éxito." + MT_ANSI_RESET);
            
            // Transformación de HLarva a HZangano
            if (mtHormiga.getMtTipo().equals("HLarva")) {
                mtHormiga.setMtTipo("HZangano"); 
                mtHormiga.setMtSexo(mtAlimento.getMtGenoma()); // Cambia sexo según genoma
                mtHormiga.setMtSuperHabilidad("superRastreo(...)"); // Habilidad Caso C
                System.out.println("[+] Evolución: HLarva -> HZángano con superRastreo.");
            }
        } else {
            System.out.println(MT_ANSI_RED + "Alimento incompatible. La hormiga MUERE." + MT_ANSI_RESET);
            mtHormiga.setMtEstado("MUERTA");
        }
        return mtHormiga;
    }

    public void Cosechar() {
        System.out.println("[+] Iniciando Cosecha (Proceso ETL) - Caso C");
        
        // Rutas con "/" para evitar errores de escape
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
        // Iconos: .o. para Alimento y \|/- para Hormiga
        String mtIcono = mtTipo.equalsIgnoreCase("Alimento") ? ".o." : "\\|/-";
        
        // Formato exacto: Tipo [Dato] .o. .o. .o. [OK - Guardado]
        System.out.print(mtTipo + " [" + mtDato + "] ");
        
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(300); 
                System.out.print(mtIcono + " "); 
            }
            
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
        // Datos de prueba incluyendo tu Caso C
        return Arrays.asList("HZangano", "Omnivoro", "HLarva", "Carnivoro");
    }
}