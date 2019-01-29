package com.ancda.cat.alarm.service.impl;

import com.ancda.cat.alarm.controller.AlarmController;
import com.ancda.cat.alarm.entity.enums.HttpCode;
import com.ancda.cat.alarm.entity.message.JsonResult;
import com.ancda.cat.alarm.service.AlarmService;
import com.ancda.cat.alarm.util.*;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;

/**
 * @Title: WeiXinServiceImpl
 * @Package com.ancda.cat.alarm.service.impl
 * @Description: TODO
 * @author huanghy
 * @date 2019/1/29 13:21
 * @version V1.0
 */
@Service
public class AlarmServiceImpl implements AlarmService {

    private static Logger logger = LoggerFactory.getLogger(AlarmServiceImpl.class);

    @Value("${cat.alarm_url}")
    private String alarmUrl;

    @Value("${cat.server}")
    private String server;

    @Override
    public JsonResult sendWeiXin(HttpServletRequest request) {
        logger.info("AlarmServiceImpl.sendWeiXin param: map is {}", JsonUtil.toJson(request.getParameterMap()));
        String content = request.getParameter("content");
        String domain = request.getParameter("domain");
        String time = DateUtil.format(new Date(), "yyyyMMddHH");
        String url = StringUtil.replaceHolder(alarmUrl, domain, time);
        content += "\n<a href=\""+ url + "\">查看详情</a>";
        WeiXinUtil.sendText("", content);
        return HttpCode.OK.getJsonResult();
    }

    @Override
    public JsonResult sendEmail(HttpServletRequest request) {
        logger.info("AlarmServiceImpl.sendWeiXin param: map is {}", JsonUtil.toJson(request.getParameterMap()));
        String content = request.getParameter("content");
        String re = request.getParameter("re");
        String title = request.getParameter("title");
        if(StringUtil.isNotBlank(content)){
            EmailUtil.sendHtml(content.replaceAll("cat-web-server", server), Collections.singletonList(re), title);
        }
        return HttpCode.OK.getJsonResult();
    }
}
