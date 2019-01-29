package com.ancda.cat.alarm.util;

import com.ancda.cat.alarm.config.WeiXinConfig;
import com.ancda.cat.alarm.entity.enums.RedisKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title: WeiXinUtil
 * @Package com.ancda.cat.alarm.util
 * @Description: 企业微信工具类
 * @author huanghy
 * @date 2019/1/28 14:57
 * @version V1.0
 */
public class WeiXinUtil {

    private static Logger logger = LoggerFactory.getLogger(WeiXinUtil.class);



    /**
     * 获取企业微信token
     * @return
     */
    public static String getToken(){
        String token = RedisUtil.get(RedisKey.QY_WEIXIN_TOKEN.getKey() + WeiXinConfig.CORP_SECRET);
        if(StringUtil.isNotBlank(token)){
            return token;
        }
        String result = OkHttpUtil.getJson(WeiXinConfig.GET_TOKEN_URL);
        logger.info(result);
        Map<String, Object> map = JsonUtil.toBean(result, Map.class);
        if(map != null){
            if(0 == (Integer)map.get("errcode")){
                String accessToken = (String)map.get("access_token");
                Integer expiresIn = (Integer)map.get("expires_in");
                RedisUtil.set(RedisKey.QY_WEIXIN_TOKEN.getKey() + WeiXinConfig.CORP_SECRET, accessToken,
                        expiresIn != null ? (long)expiresIn : 7200L , TimeUnit.SECONDS);
                return accessToken;
            }
        }
        return null;
    }

    public static void sendText(String toUser, String content){
        String token = WeiXinUtil.getToken();
        if(StringUtil.isNotBlank(token)){
            Map<String, Object> map = new HashMap<>(16);
            map.put("touser", StringUtil.isNotBlank(toUser) ? toUser : "@all");
            map.put("msgtype", "text");
            map.put("agentid", WeiXinConfig.AGENT_ID);
            map.put("safe", "0");
            Map<String, Object> textMap = new HashMap<>(2);
            textMap.put("content", content);
            map.put("text", textMap);
            String result = OkHttpUtil.postJson(StringUtil.replaceHolder(WeiXinConfig.SEND_MSG_URL, token), JsonUtil.toJson(map));
            logger.info(result);
            Map<String, Object> resultMap = JsonUtil.toBean(result, Map.class);
            if(resultMap != null){
                if(40014 == (Integer)resultMap.get("errcode")){
                    RedisUtil.delete(RedisKey.QY_WEIXIN_TOKEN.getKey() + WeiXinConfig.CORP_SECRET);
                    sendText(toUser, content);
                }
            }
        }
    }
}
