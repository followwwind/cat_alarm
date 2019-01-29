package com.ancda.cat.alarm.service;

import com.ancda.cat.alarm.entity.message.JsonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: AlarmService
 * @Package com.ancda.cat.alarm.service
 * @Description: TODO
 * @author huanghy
 * @date 2019/1/29 13:20
 * @version V1.0
 */
public interface AlarmService {

    /**
     * 微信消息发送
     * @param request
     * @return
     */
    JsonResult sendWeiXin(HttpServletRequest request);

    /**
     * 发送邮件
     * @param request
     * @return
     */
    JsonResult sendEmail(HttpServletRequest request);
}
