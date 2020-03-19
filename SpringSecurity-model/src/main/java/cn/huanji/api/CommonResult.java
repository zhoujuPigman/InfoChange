package cn.huanji.api;

/**
 * 通用回复类
 * @param <T>
 */
public class CommonResult<T> {

    /**
     * 回复码
     */
    private long code;

    /**
     * 回复信息
     */
    private String message;

    /**
     * 回复数据
     */
    private T data;

    protected CommonResult(){

    }

    protected  CommonResult(long code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 未授权返回结果
     */
    public  static <T> CommonResult<T> forbidden(T data){
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getMsg(),data);
    }

    /**
     * 未登录或token过期
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unauthorized(T data){
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMsg(),data);
    }
}
