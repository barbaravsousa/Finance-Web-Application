package switchtwentytwenty.project.exception;

public class DepositNotPossibleException extends Exception {

    /**
     * Sole Constructor
     * @param errorMessage
     */
    public DepositNotPossibleException(String errorMessage){
        super("Can not deposit negative amount");
    }
}
