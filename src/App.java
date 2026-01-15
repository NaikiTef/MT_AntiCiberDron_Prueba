import java.util.Scanner;
import MTBussinessComponent.TMEntities.MTEntomologoGenetista;
import MTBussinessComponent.TMEntities.MTHormiga;
import MTBussinessComponent.TMEntities.MTAlimento;
import MTDataAccessCompoment.MTDATOs.MTAlimentoTipoDTO;

public class App {
    public static void main(String[] args) {
        Scanner mtScanner = new Scanner(System.in);
        
        // --- REQUERIMIENTO 1: Autenticación ---
        boolean mtAutenticado = false;
        int mtIntentos = 0;

        System.out.println("========================================");
        System.out.println("   SISTEMA DE CONTROL ANTDRON2K25       ");
        System.out.println("========================================");

        while (mtIntentos < 3 && !mtAutenticado) {
            System.out.print("[?] Usuario: ");
            String mtUser = mtScanner.nextLine();
            System.out.print("[?] Contraseña: ");
            String mtPass = mtScanner.nextLine();

            // Credenciales obligatorias: patmic / 123
            if (mtUser.equals("patmic") && mtPass.equals("123")) {
                mtAutenticado = true;
                System.out.println("\n[OK] Acceso concedido.");
                System.out.println("ESTUDIANTE: Victoria Yureisy Torres Quiñonez"); // Obligatorio
                System.out.println("CÉDULA: 0850426867");     // Obligatorio
                System.out.println("ESTUDIANTE: TiffanyNicole Montaluisa Robalino"); // Obligatorio
                System.out.println("CÉDULA: 1755704523");     // Obligatorio
            } else {
                mtIntentos++;
                System.out.println("\u001B[31m[!] Error. Intentos: " + mtIntentos + "/3\u001B[0m");
            }
        }

        if (mtAutenticado) {
            // --- INICIO DE PRUEBAS (CASO C: HZángano / Omnívoro) ---
            MTAlimentoTipoDTO mtDto = new MTAlimentoTipoDTO();
            MTEntomologoGenetista mtGenetista = new MTEntomologoGenetista(mtDto);

            // 1. REQUERIMIENTO 2: Proceso ETL (Cosecha)
            System.out.println("\n--- INICIANDO PROCESO ETL ---");
            mtGenetista.Cosechar();

            // 2. REQUERIMIENTO 3: Preparación, Alimentación y Evolución
            System.out.println("\n--- PRUEBA DE GENÉTICA Y EVOLUCIÓN ---");
            
            // Crear larva y alimento para Caso C
            MTHormiga mtLarva = new MTHormiga("HLarva", "X");
            MTAlimento mtComida = new MTAlimento("Omnivoro");

            // Acción: Preparar alimento inyectando genoma (Solo por Entomólogo Genetista)
            mtComida = mtGenetista.mtPrepararAlimentoG(mtComida);

            System.out.println("\nEstado antes de comer:");
            mtLarva.mtMostrarInfo();

            // Acción: Alimentar (VIVE si el alimento corresponde al caso)
            mtGenetista.mtAlimentarAnt(mtLarva, mtComida);

            System.out.println("\nEstado después de comer (Evolución):");
            mtLarva.mtMostrarInfo();

        } else {
            System.out.println("\u001B[31m[X] Acceso denegado.\u001B[0m");
        }
        mtScanner.close();
    }
}