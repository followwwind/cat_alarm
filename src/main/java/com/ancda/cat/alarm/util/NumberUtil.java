package com.ancda.cat.alarm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: NumberUtil
 * @Package com.ancda.cat.alarm.util
 * @Description: TODO
 * @author huanghy
 * @date 2019/1/28 15:23
 * @version V1.0
 */
public class NumberUtil {

    private static Logger logger = LoggerFactory.getLogger(NumberUtil.class);

    /**
     * long字符串转long
     * @param value
     * @return
     */
    public static Long toLong(String value){
        if(RegexUtil.checkInteger(value)){
            try {
                return Long.valueOf(value);
            } catch (NumberFormatException e) {
                logger.warn("to long error, value:" + value);
            }
        }
        return null;
    }
}
