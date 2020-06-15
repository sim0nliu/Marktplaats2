
package marktplaats.domain.exceptions;

public class NotImplementedException extends RuntimeException {
    public NotImplementedException() {
    }

    public NotImplementedException(String problem) {
        super(problem);
    }

}
