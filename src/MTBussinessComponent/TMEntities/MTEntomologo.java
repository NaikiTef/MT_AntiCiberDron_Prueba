package MTBussinessComponent.TMEntities;

import java.util.ArrayList;
import java.util.List;

import MTBussinessComponent.TMInterfaces.IEntomologo;
import MTDataAccessCompoment.MTDATOs.MTAlimentoTipoDTO;

public class MTEntomologo extends MTTipoEntomologo implements IEntomologo {
    
    public MTEntomologo(MTAlimentoTipoDTO mtAlimentoTipo) {
        super(mtAlimentoTipo);
    }

    @Override
    public List<MTHormiga> mtEntAntNest() {
        // Aquí va la lógica ETL de lectura del archivo AntNest.txt
        System.out.println("[+] Extrayendo hormigas...");
        return new ArrayList<>(); 
    }

    @Override
    public List<MTAlimento> mtEntAntFood() {
        // Aquí va la lógica ETL de lectura del archivo AntFood.txt
        System.out.println("[+] Extrayendo alimentos...");
        return new ArrayList<>();
    }
    
    @Override
    public MTHormiga mtAlimentarAnt(MTHormiga mtHormiga, MTAlimento mtAlimento) {
        System.out.println("[!] Procesando alimentación...");
        
        // REQUERIMIENTO: La hormiga VIVE si come su alimento con o sin genoma
        // Caso contrario MUERE.
        if (mtHormiga.getMtTipoAlimento().equals(mtAlimento.getMtTipo())) {
            System.out.println("La hormiga ha comido con éxito.");
            
            // Si es HLarva, debe transformarse (según requerimiento 3)
            if (mtHormiga.getMtTipo().equals("HLarva")) {
                // Lógica de transformación y superHabilidad
            }
        } else {
            System.out.println("Alimento incompatible. La hormiga MUERE.");
            mtHormiga.setMtEstado("MUERTA");
        }
        
        return mtHormiga;
    }

    public void Cosechar() {
    System.out.println("[+] Iniciando Cosecha de Hormigas y Alimento...");
    
    // 1. Cargar Alimentos
    List<String> mtLineasAlimento = leerArchivo("MTStorage\\MTDataFiles\\AntFood.txt");
    for (String mtLinea : mtLineasAlimento) {
        mtProcesarDato(mtLinea, "Alimento");
    }
    // 2. Cargar Hormigas
    List<String> mtLineasHormigas = leerArchivo("MTStorage\\MTDataFiles\\AntNest.txt");
    for (String mtLinea : mtLineasHormigas) {
        mtProcesarDato(mtLinea, "Hormiga");
    }
}

private void mtProcesarDato(String mtDato, String mtTipo) {
    System.out.print("Loading " + mtTipo + " [" + mtDato + "] ");
    
    try {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(300); // Pausa para el efecto visual
            System.out.print(".");
        }
        
        if (mtEsDatoValido(mtDato)) {
            
            System.out.println("\u001B[34m [OK - Guardado]\u001B[0m");
            // Aquí llamarías a tu capa DataAccess para guardar en la DB
        } else {
            
            System.err.println(" [ERROR - No corresponde al caso]");
        }
        
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}

private boolean mtEsDatoValido(String mtDato) {
    return mtDato.contains("HZangano") || mtDato.contains("Omnivoro"); 
}

private List<String> leerArchivo(String mtPath) {
    return java.util.Arrays.asList("HLarva", "Carnivoro", "Hobrera", "Nectarivoros");
}

}