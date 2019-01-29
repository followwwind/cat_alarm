package com.ancda.cat.alarm.entity.enums;

import com.ancda.cat.alarm.entity.message.JsonResult;

/**
 * @Title: BaseCode
 * @Package com.ancda.palmbaby.ancda.common.constant
 * @Description: TODO
 * @author huanghy
 * @date 2018/6/25 19:29
 * @version V1.0
 */
public interface BaseCode {

    String HTTP_CODE = "HTTP_CODE_";

    /**
     * 获取状态码
     * @return
     */
    Integer getCode();

    /**
     * 获取状态码说明
     * @return
     */
    String getMsg();

    /**
     * 获取jsonResult
     * @return
     */
    default JsonResult getJsonResult(){
        return new JsonResult(getCode(), getMsg());
    }

}
