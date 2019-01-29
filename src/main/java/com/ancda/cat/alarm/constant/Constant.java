package com.ancda.cat.alarm.constant;

import java.io.File;
import java.math.BigDecimal;

/**
 * 常量工具集
 * @author wind
 */
public interface Constant {

    /**********************************************分隔符常量************************************************/

    String POINT_STR = ".";

    String AND_STR = "&";

    String BLANK_STR = "";

    String COMMA_STR = ",";

    String MINUS_STR = "-";

    String SPACE_STR = " ";

    String QUESTION_STR = "?";

    String SYS_SEPARATOR = File.separator;

    String FILE_SEPARATOR = "/";

    String BRACKET_LEFT = "[";

    String BRACKET_RIGHT = "]";

    String UNDERLINE = "_";

    String COLON = ":";

    /**
     * 左括号
     */
    String LEFT_BRACKET = "(";

    /**
     * 右括号
     */
    String RIGHT_BRACKET = ")";





    /**********************************************编码格式************************************************/
    String UTF8 = "UTF-8";

    /**********************************************系统信息************************************************/
    String OS_NAME = System.getProperty("os.name");

    String WIN_DIR = System.getenv("windir");

    String USER_DIR = "user.dir";

    String TOKEN = "X-API-TOKEN";


    /**
     * 状态默认值
     */
    int STATUS_DEFAULT = 0;

    /**
     * 最小金额
     */
    BigDecimal minAmount = new BigDecimal(0.01);

    String HTTP = "http";


    /**********************************************文件类型************************************************/

    /**
     * xlsx文件
     */
    String EXCEL_2007_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    String EXCEL_XLSX = "xlsx";

    String EXCEL_XLS = "xls";

    String EXCEL_XLSX_SUFFIX = ".xlsx";

    String PNG_SUFFIX = ".png";

    String IMG_SUFFIX = ".jpg";

    String PNG_TYPE = " image/png";

    String VIDEO_TYPE = "mp4/rmvb/avi";

    String VIDEO_MP4_SUFFIX = ".MP4";
}


