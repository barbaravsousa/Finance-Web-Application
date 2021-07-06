package switchtwentytwenty.project.exception;

public class InvalidEmailException extends Exception {

    /**
     * Sole Constructor
     * @param errorMessage
     */
    public InvalidEmailException(String errorMessage) {
        super(errorMessage);
    }
}
