package switchtwentytwenty.project.exception;

public class InvalidVATException extends Exception {

    /**
     * Sole Constructor
     * @param errorMessage
     */
    public InvalidVATException(String errorMessage){
        super(errorMessage);
    }
}
