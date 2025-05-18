package controller.utils;

/**
 *
 * @author migue
 */
public class Response {
    
    private String message;
    private int status;
    private Object object;

    public Response(String message, int status) {
        this.message = message;
        this.status = status;
    }
    
    public Response(String message, int status, Object object) {
        this.message = message;
        this.status = status;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Object getObject() {
        return object;
    }
    
}
