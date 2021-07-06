package switchtwentytwenty.project.exception;


public class WithdrawNotPossibleException extends Exception {

    /**
     * Sole Constructor
     *
     * @param errorMessage - error massage
     */
    public WithdrawNotPossibleException(String errorMessage){
        super(errorMessage);
    }
}