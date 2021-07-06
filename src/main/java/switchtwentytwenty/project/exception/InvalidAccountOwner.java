package switchtwentytwenty.project.exception;

public class InvalidAccountOwner extends Exception{

    /**
     * Sole Constructor
     * @param errorMessage
     */
    public InvalidAccountOwner(String errorMessage){
        super(errorMessage);
    }
}
