package com.ancda.cat.alarm.controller;

import com.ancda.cat.alarm.entity.enums.HttpCode;
import com.ancda.cat.alarm.entity.message.JsonResult;
import com.ancda.cat.alarm.service.AlarmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: EmailController
 * @Package com.ancda.cat.alarm.controller
 * @Description: TODO
 * @author huanghy
 * @date 2019/1/28 14:56
 * @version V1.0
 */
@RestController
@RequestMapping(value = "api/alarm")
public class AlarmController {

    private static Logger logger = LoggerFactory.getLogger(AlarmController.class);

    @Autowired
    private AlarmService alarmService;

    @RequestMapping(value = "email/msg", method = RequestMethod.POST)
    public JsonResult sendEmail(HttpServletRequest request){
        logger.info("AlarmController.sendEmail");
        return alarmService.sendEmail(request);
    }

    @RequestMapping(value = "weiXin/msg", method = RequestMethod.POST)
    public JsonResult sendWeiXin(HttpServletRequest request){
        logger.info("AlarmController.sendWeiXin");
        return alarmService.sendWeiXin(request);
    }
}
