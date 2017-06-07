package chang.backend.dto;

/**
 * Created by chang on 2017/3/27.
 */
public class Response {

    private static final int SUCCESS = 200;
    private static final int ERROR = 500;

    private int code;
    private String msg;
    private Object data;

    public Response(){}
    public Response(int code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response success(){
        return new Response(SUCCESS,null,null);
    }

    public static Response success(Object data){
       return new Response(SUCCESS,null,data);
    }

    public static Response fail(String msg){
        return new Response(ERROR,msg,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
