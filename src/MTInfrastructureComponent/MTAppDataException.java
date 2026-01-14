package MTInfrastructureComponent;

public class MTAppDataException extends MTAppException {
    public MTAppDataException(String message) {
        super(message);
    }
    public MTAppDataException(Exception e, Class<?> clazz, String method) {
        super(e, clazz, method);
    }
}
