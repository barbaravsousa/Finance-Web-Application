package switchtwentytwenty.project.exception;

public class AccountNotCreatedException extends Exception{

    /**
     * Sole Constructor
     * @param errorMessage
     */
    public AccountNotCreatedException(String errorMessage){
        super(errorMessage);
    }
}
