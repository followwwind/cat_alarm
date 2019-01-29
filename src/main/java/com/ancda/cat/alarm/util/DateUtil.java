package com.ancda.cat.alarm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title: DateUtil
 * @Package com.ancda.cat.alarm.util
 * @Description: TODO
 * @author huanghy
 * @date 2019/1/29 18:29
 * @version V1.0
 */
public class DateUtil {

    /**
     * 格式化
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern){
        if(date == null){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(StringUtil.isNotBlank(pattern) ? pattern : "yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
