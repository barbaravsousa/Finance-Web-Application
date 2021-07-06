package switchtwentytwenty.project.exception;

public class PersonAlreadyInSystemException extends Exception {

    /**
     * Sole Constructor
     * @param errorMessage
     */
    public PersonAlreadyInSystemException(String errorMessage) {
        super(errorMessage);
    }
}
