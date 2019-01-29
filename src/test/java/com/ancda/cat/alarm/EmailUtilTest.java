package com.ancda.cat.alarm;

import com.ancda.cat.alarm.util.EmailUtil;
import org.junit.Test;

/**
 * @Title: EmailUtilTest
 * @Package com.ancda.cat.alarm
 * @Description: TODO
 * @author huanghy
 * @date 2019/1/29 16:28
 * @version V1.0
 */
public class EmailUtilTest {

    @Test
    public void sendHtml(){
        EmailUtil.sendHtml("", null, "333");
    }
}
