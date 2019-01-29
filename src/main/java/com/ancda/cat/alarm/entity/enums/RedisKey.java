package com.ancda.cat.alarm.entity.enums;

/**
 * 
 * @fileName RedisKey.java
 * @package com.ancda.palmbaby.ancda.common.constant
 * @description Redis缓存key定义枚举
 * @author yujl@ancda.com
 * @date 2018年4月25日 上午11:22:39
 * @version V1.0
 */
public enum RedisKey {
    
    QY_WEIXIN_TOKEN("qy_weixin_token:", 24*60*60L , "企业微信token")

    ;


    RedisKey(String key, Long timeout, String description) {
        this.key = key;
        this.timeout = timeout;
        this.description = description;
    }

    /** redis key 名称 */
    private final String key;
    
    /** 超时时间（秒） */
    private final Long timeout;
    
    /** redis key 描述 */
    private final String description;

    public String getKey() {
        return key;
    }

    public Long getTimeout() {
        return timeout;
    }

    public String getDescription() {
        return description;
    }
}
