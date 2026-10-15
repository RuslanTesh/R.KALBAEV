package academy.tochkavhoda.competition.service;

/** Business-level error; its message is returned to the client in the "error" field. */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }
}
