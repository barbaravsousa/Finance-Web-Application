package switchtwentytwenty.project.exception;

public class InvalidPersonNameException extends Exception {

    /**
     * Sole constructor.
     * @param errorMessage - error message.
     */
    public InvalidPersonNameException(String errorMessage) {
        super(errorMessage);
    }
}
