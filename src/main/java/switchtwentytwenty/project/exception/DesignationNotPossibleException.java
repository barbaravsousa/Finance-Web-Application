package switchtwentytwenty.project.exception;

public class DesignationNotPossibleException extends Exception{

    /**
     * Sole Constructor
     */
    public DesignationNotPossibleException(){
        super("Category Designation already used in the same level");
    }

}
