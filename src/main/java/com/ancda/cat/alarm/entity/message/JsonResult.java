package com.ancda.cat.alarm.entity.message;

import com.ancda.cat.alarm.entity.enums.HttpCode;
import java.io.Serializable;

/**
 * @Title: JsonResult
 * @Package com.wind.webapi.message
 * @Description: json返回结果集
 * @author wind
 * @date 2018/9/17 17:43
 * @version V1.0
 */
public class JsonResult implements Serializable {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回数据集
     */
    private Object data;
    /**
     * 消息说明
     */
    private String msg;

    /**
     * 系统当前时间,GTM时间戳
     */
    private Long timestamp;

    /**
     * 用于处理json反序列化，需要一个默认构造器
     */
    private JsonResult(){

    }

    public JsonResult(HttpCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.timestamp = System.currentTimeMillis();
    }

    public JsonResult(HttpCode code, Object data) {
        this(code.getCode(), data, code.getMsg());
    }

    public JsonResult(HttpCode code, Object data, String msg) {
        this(code.getCode(), data, msg);
    }

    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
    }

    public JsonResult(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
