package com.ancda.cat.alarm.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.List;

/**
 * @Title: EmailUtil
 * @Package com.ancda.cat.alarm.util
 * @Description: TODO
 * @author huanghy
 * @date 2019/1/29 16:21
 * @version V1.0
 */
public class EmailUtil {

    /**
     * 发送邮件
     * @param content
     * @param toList
     * @param title
     */
    public static void sendHtml(String content, List<String> toList, String title){
        try {
            //Create the mail message
            HtmlEmail htmlEmail = new HtmlEmail();
            //你的邮件服务器的地址
            htmlEmail.setHostName("smtp.163.com");
            //如果你的邮件服务器设置了密码，请输入密码，否则此语句可以忽略
            htmlEmail.setAuthentication("18771933975@163.com", "0follow0");
            //设置收件人，如果想添加多个收件人，将此语句多写几次即可。
            htmlEmail.addTo("970720206@qq.com", null);
            if(toList != null && !toList.isEmpty()){
                toList.stream().filter("970720206@qq.com"::equals).forEach(to -> {
                    try {
                        htmlEmail.addTo(to, null);
                    } catch (EmailException e) {
                        e.printStackTrace();
                    }
                });
            }
            //发件人
            htmlEmail.setFrom("18771933975@163.com", "cat");
            htmlEmail.setCharset("UTF-8");
            htmlEmail.setSubject(title);
//            htmlEmail.setMsg("hello welcome");
            htmlEmail.setHtmlMsg(content);
            //发送邮件
            htmlEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
