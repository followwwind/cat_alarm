package com.ancda.cat.alarm;

import com.ancda.cat.alarm.config.WeiXinConfig;
import com.ancda.cat.alarm.util.WeiXinUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * @Title: WeiXinUtilTest
 * @Package com.ancda.cat.alarm
 * @Description: TODO
 * @author huanghy
 * @date 2019/1/28 15:51
 * @version V1.0
 */
public class WeiXinUtilTest {

    @Before
    public void init(){
        WeiXinConfig.CORP_ID = "ww10ed684f2353e8ef";
        WeiXinConfig.CORP_SECRET = "pfLQRq-eD9fDWdpbWbIrgOwRHdMjqRG0rv0zIdBZxjU";
        WeiXinConfig.AGENT_ID = "1000004";
        WeiXinConfig.GET_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww10ed684f2353e8ef&corpsecret=pfLQRq-eD9fDWdpbWbIrgOwRHdMjqRG0rv0zIdBZxjU";
        WeiXinConfig.SEND_MSG_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={}";
    }

    @Test
    public void getToken(){
        System.out.println(WeiXinUtil.getToken());
    }

    @Test
    public void sendText(){
        WeiXinUtil.sendText("@all", "pb test");
    }
}
