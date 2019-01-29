package com.ancda.cat.alarm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Title: WeixinConfig
 * @Package com.ancda.cat.alarm.config
 * @Description: TODO
 * @author huanghy
 * @date 2019/1/28 14:46
 * @version V1.0
 */
@Component
public class WeiXinConfig {

    public static String CORP_ID;

    public static String AGENT_ID;

    public static String CORP_SECRET;

    public static String GET_TOKEN_URL;

    public static String SEND_MSG_URL;

    @Value("${qy_weixin.corp_id}")
    public void setCorpId(String corpId) {
        CORP_ID = corpId;
    }

    @Value("${qy_weixin.corp_secret}")
    public void setCorpSecret(String corpSecret) {
        CORP_SECRET = corpSecret;
    }

    @Value("${qy_weixin.agent_id}")
    public void setAgentId(String agentId) {
        AGENT_ID = agentId;
    }

    @Value("${qy_weixin.get_token_url}")
    public void setGetTokenUrl(String getTokenUrl) {
        GET_TOKEN_URL = getTokenUrl;
    }

    @Value("${qy_weixin.send_msg_url}")
    public void setSendMsgUrl(String sendMsgUrl) {
        SEND_MSG_URL = sendMsgUrl;
    }
}
