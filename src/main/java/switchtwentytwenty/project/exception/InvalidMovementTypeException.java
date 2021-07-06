package switchtwentytwenty.project.exception;

public class InvalidMovementTypeException extends Exception{

    /**
     * Sole Constructor
     *
     * @param errorMessage - error massage
     */
    public InvalidMovementTypeException(String errorMessage){
        super(errorMessage);
    }
}
