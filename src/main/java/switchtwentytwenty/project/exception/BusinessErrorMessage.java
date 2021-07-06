package switchtwentytwenty.project.exception;

public class BusinessErrorMessage extends Exception {

    //Attributes
    public static final int GROUP_DESCRIPTION_NOT_FOUND = 1;
    private final String message;
    private final int code;

    //Constructor Method
    public BusinessErrorMessage(String message, int code){
        this.message = message;
        this.code = code;
    }

    //Getter
    public String getErrorMessage(){
        return this.message;
    }

    public int getErrorCode(){
        return this.code;
    }
}
