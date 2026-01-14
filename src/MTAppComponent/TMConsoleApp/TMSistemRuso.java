package MTAppComponent.TMConsoleApp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import MTBussinessComponent.TMEntities.TMCoordenadaUK;

public class TMSistemRuso {
    Scanner mtSc = new Scanner(System.in);
    
    private static final Logger LOGGER = Logger.getLogger(TMSistemRuso.class.getName());
    public List<TMCoordenadaUK> readCoord(String fileNamePath) throws IOException {
        List<TMCoordenadaUK> lstCoord = new java.util.ArrayList<>();
        List<String> allLines = Files.readAllLines(Paths.get(fileNamePath));
        for (String line : allLines) {
            LOGGER.info(line);
            String[] coord = line.split(",");
            TMCoordenadaUK coordenada = new TMCoordenadaUK( coord[0], coord[6], "");
            lstCoord.add(coordenada);
        }
        return lstCoord;
    }

    public String mtusuario = "patmic";
    public Integer mtpassword = 123;


    public void iniciarSesion() {
        int intentos = 3;
        while (intentos > 0) {
            try{
                System.out.println("Ingrese su usuario:");
                String mtusuarioInput = mtSc.nextLine();

                System.out.println("Ingrese su contrasena:");
                Integer mtpasswordInput = Integer.parseInt(mtSc.nextLine());

                if (mtusuarioInput.isEmpty() || mtpasswordInput == null) {
                    throw new Exception("Usuario y contraseña no pueden estar vacíos");
                }

                if (!mtusuarioInput.equals(mtusuario)) {
                    throw new Exception("Usuario incorrecto");
                }

                if (!mtpasswordInput.equals(mtpassword)) {
                    throw new Exception("Contraseña incorrecta");
                }

                System.out.println("Acceso concedido");
                System.out.println("Bienvenido al Sistema Ruso.");
                System.out.println("Propietarios: * Montaluisa Robalino Tiffany Nicole");
                System.out.println("              * Torres Quiñonez Victoria Yureisy ");
                System.out.println("Cedula: 1755704523");
                System.out.println("        0850426867");

                break;
            } catch (Exception e) {
                intentos--;
                System.out.println(e.getMessage());
                System.out.println("Intentos restantes: " + intentos);
                System.out.println();
            }
        }
        }
    }

