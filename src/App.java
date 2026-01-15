import java.util.Scanner;
import MTBussinessComponent.TMEntities.MTEntomologo;
import MTBussinessComponent.TMEntities.MTHormiga;
import MTBussinessComponent.TMEntities.MTAlimento;
import MTDataAccessCompoment.MTDATOs.MTAlimentoTipoDTO;

public class App {
    public static void main(String[] args) {
        Scanner mtScanner = new Scanner(System.in);
        
        // --- REQUERIMIENTO 1: Autenticación ---
        // Se solicita usuario "patmic" y contraseña "123".
        boolean mtAutenticado = false;
        int mtIntentos = 0;
        final String MT_USER_VALIDO = "patmic";
        final String MT_PASS_VALIDO = "123";

        System.out.println("========================================");
        System.out.println("   SISTEMA DE CONTROL ANTDRON2K25       ");
        System.out.println("========================================");

        while (mtIntentos < 3 && !mtAutenticado) {
            System.out.print("[ ] Usuario: ");
            String mtUser = mtScanner.nextLine();
            System.out.print("[ ] Contraseña: ");
            String mtPass = mtScanner.nextLine();

            if (mtUser.equals(MT_USER_VALIDO) && mtPass.equals(MT_PASS_VALIDO)) {
                mtAutenticado = true;
                // Presentar identificación tras éxito.
                System.out.println("\n[OK] Acceso concedido.");
                System.out.println("ESTUDIANTE 1 : Victoria Yureisy Torres Quiñonez");
                System.out.println("CÉDULA     1 : 0850426867");
                System.out.println("ESTUDIANTE 2 : Nikole Montaluisa");
                System.out.println("CÉDULA     2 : [Ingresar Cédula]");
            } else {
                mtIntentos++;
                System.out.println("\u001B[31m[!] Credenciales incorrectas. Intentos: " + mtIntentos + "/3\u001B[0m");
            }
        }

        if (mtAutenticado) {
            // --- INICIO DE PRUEBAS DE LÓGICA (CASO C) ---
            MTEntomologo mtEntomologo = new MTEntomologo(new MTAlimentoTipoDTO());

            // 1. Ejecución del Proceso ETL (Requerimiento 2)
            System.out.println("\n--- INICIANDO PROCESO ETL (CASO C: HZángano/Omnívoro) ---");
            mtEntomologo.Cosechar();

            // 2. Ejecución de Alimentación y Evolución (Requerimiento 3)
            System.out.println("\n--- PRUEBA DE ALIMENTACIÓN Y EVOLUCIÓN ---");
            
            // CORRECCIÓN: Constructor MTHormiga(Tipo, Sexo)
            MTHormiga mtLarva = new MTHormiga("HLarva", "X");
            
            // CORRECCIÓN: Constructor MTAlimento(Nombre, Genoma)
            // Se inyecta genoma "XY" para simular la transformación acorde al genoma.
            MTAlimento mtComidaOmnivora = new MTAlimento("Omnivoro", "XY");
            
            System.out.println("Estado inicial:");
            mtLarva.mtMostrarInfo();

            // Alimentar: debe transformarse a HZángano y ganar superRastreo.
            mtEntomologo.mtAlimentarAnt(mtLarva, mtComidaOmnivora);
            
            System.out.println("\nEstado final tras alimentación:");
            mtLarva.mtMostrarInfo();

        } else {
            System.out.println("\u001B[31m[X] Sistema bloqueado por demasiados intentos fallidos.\u001B[0m");
        }

        mtScanner.close();
    }
}