package com.ppf.frame.common;

public class AjaxResult {

    /** 返回状态码 */
    private Integer code;

    /** 返回信息 */
    private String message;

    /** 返回数据 */
    private Object data;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public AjaxResult(){

    };

    public AjaxResult(Integer c,String m,Object d){
        this.code = c;
        this.message = m;
        this.data = d;
    };
}
