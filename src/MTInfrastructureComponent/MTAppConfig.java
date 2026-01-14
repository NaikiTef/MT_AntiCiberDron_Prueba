package MTInfrastructureComponent;

import java.net.URL;

public class MTAppConfig {
    
    public static final String MTDATABASE = "MTStorage/MTDatabase/MTantCiberDron.sqlite";
    
    public static final String MTDATAFILE = "Data/hormiguero.csv";
    
    public static final String MTLOGFILE  = "MTStorage/MTLogs/MTAppErrors.log";


    // Solo una referencia válida a URL_LOGO, y corregir clase
    public static final URL URL_LOGO    = MTAppConfig.class.getResource("/GUI/Resource/Logo.png");
    public static final URL URL_MAIN    = MTAppConfig.class.getResource("/GUI/Resource/logo.png");
    public static final URL URL_SPLASH  = MTAppConfig.class.getResource("/Infrastructure/Assets/Img/Splash.png");


    public static final String MSG_DEFAULT_ERROR    = "Ups! Error inesperado. Por favor, contacte al administrador del sistema.";
    public static final String MSG_DEFAULT_CLASS    = "undefined";
    public static final String MSG_DEFAULT_METHOD   = "undefined";
    
    private MTAppConfig() {}
}