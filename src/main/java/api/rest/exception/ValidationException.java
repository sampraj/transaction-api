package api.rest.exception;

public class ValidationException extends Exception{

    private String message;

    private String code;
    private Throwable t;
    public ValidationException(){
        super();
    }
    public ValidationException(final String message){
        super(message);
        this.message = message;
    }
    public ValidationException(final String message,final String code){
        super(message);
        this.message = message;
        this.code = code;
    }
    public ValidationException(String message,Throwable t){
        super(t);
        this.message = message;
        this.t = t;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
