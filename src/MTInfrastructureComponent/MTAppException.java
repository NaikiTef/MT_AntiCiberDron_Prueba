
package MTInfrastructureComponent;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import MTInfrastructureComponent.TMTools.MTCMDColor;

public abstract class MTAppException extends Exception {
    public MTAppException(Exception e, Class<?> clase, String metodo) {
        super(e != null ? e.getMessage() : getDefaultError(), e);
        saveLogFile(e != null ? e.getMessage() : null, clase, metodo);
    }
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Valores por defecto si no existen en MTAppConfig
    private static final String DEFAULT_ERROR = "Ocurrió un error inesperado";
    private static final String DEFAULT_CLASS = "ClaseDesconocida";
    private static final String DEFAULT_METHOD = "MétodoDesconocido";

    public MTAppException(String showMsg) {
        super((showMsg == null || showMsg.isBlank()) ? getDefaultError() : showMsg);
        saveLogFile(null, null, null);
    }
    public MTAppException(String showMsg, Exception e, Class<?> clase, String metodo) {
        super((showMsg == null || showMsg.isBlank()) ? getDefaultError() : showMsg);
        saveLogFile(e != null ? e.getMessage() : null, clase, metodo);
    }

    private static String getDefaultError() {
        try {
            return MTAppConfig.class.getField("MSG_DEFAULT_ERROR").get(null).toString();
        } catch (Exception e) {
            return DEFAULT_ERROR;
        }
    }
    private static String getDefaultClass() {
        try {
            return MTAppConfig.class.getField("MSG_DEFAULT_CLASS").get(null).toString();
        } catch (Exception e) {
            return DEFAULT_CLASS;
        }
    }
    private static String getDefaultMethod() {
        try {
            return MTAppConfig.class.getField("MSG_DEFAULT_METHOD").get(null).toString();
        } catch (Exception e) {
            return DEFAULT_METHOD;
        }
    }

    private void saveLogFile(String logMsg, Class<?> clase, String metodo) {
        String timestamp  = LocalDateTime.now().format(FORMATTER);
        String className  = (clase  == null) ? getDefaultClass()  : clase.getSimpleName();
        String methodName = (metodo == null) ? getDefaultMethod() : metodo;
        logMsg = (logMsg == null || logMsg.isBlank()) ? getDefaultError() : logMsg;
        logMsg = String.format("╭─💀─ SHOW ❱❱ %s \n╰──── LOG  ❱❱ %s | %s.%s | %s", getMessage(), timestamp, className, methodName, logMsg);

        try (PrintWriter writer = new PrintWriter(new FileWriter(MTAppConfig.MTLOGFILE, true))) {
            System.err.println(MTCMDColor.BLUE  + logMsg);
            writer.println(logMsg);
        } catch (Exception e) {
            System.err.println(MTCMDColor.RED  + "[MTAppException.saveLogFile] ❱ " + e.getMessage());
        } finally {
            System.out.println(MTCMDColor.RESET);
        }
    }
}
