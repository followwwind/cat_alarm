package com.ancda.cat.alarm.util;


import com.ancda.cat.alarm.constant.Constant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;

/**
 * @Title: StringUtil
 * @Package com.ancda.cat.alarm.util
 * @Description: 字符串工具类
 * @author huanghy
 * @date 2019/1/28 14:56
 * @version V1.0
 */
public class StringUtil{


    /**
     * 字符串不为null且不是空字符串
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str){
        return str != null && !"".equals(str.trim());
    }


    /**
     * url编码
     * @param str
     * @return
     */
    public static String encodeUrl(String str){
        String s = null;
        try {
            s = URLEncoder.encode(str, Constant.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return s;
    }

    /**
     * url解码
     * @param str
     * @return
     */
    public static String decodeUrl(String str){
        String s = null;
        try {
            s = URLDecoder.decode(str, Constant.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }


    /**
     * 字符串分隔 StringTokenizer效率是三种分隔方法中最快的
     * @param str
     * @param sign
     * @return
     */
    public static String[] split(String str, String sign){
        if(str == null){
            return new String[]{};
        }
        StringTokenizer token = new StringTokenizer(str,sign);
        String[] strArr = new String[token.countTokens()];
        int i = 0;
        while(token.hasMoreElements()){
            strArr[i] = token.nextElement().toString();
            i++;
        }
        return strArr;
    }


    /**
     * java驼峰命名的类成员字段名
     * @param name 字符串
     * @param flag 首字母小写为false， 大写为true
     * @return
     */
    public static String getCamelCase(String name, boolean flag){
        StringBuilder sb = new StringBuilder();
        if(name != null){
            String[] arr = split(name, Constant.UNDERLINE);
            for(int i = 0; i < arr.length; i++){
                String s = arr[i];
                if(i == 0){
                    if(flag){
                        s = getFirst(s, true);
                    }
                    sb.append(s);
                }else{
                    int len = s.length();
                    if(len == 1){
                        sb.append(s.toUpperCase());
                    }else{
                        sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
                    }
                }
            }
        }
        return sb.toString();
    }


    /**
     * 占位符替换
     * @param str
     * @param arr
     * @return
     */
    public static String replaceHolder(String str, String ...arr){
        if(StringUtil.isNotBlank(str) && arr.length > 0){
            for(String s : arr){
                str = str.replaceFirst("\\{\\}", s);
            }
        }
        return str;
    }

    /**
     * 将单词首字母变大小写
     * @param str
     * @param flag true变大写， false变小写
     * @return
     */
    public static String getFirst(String str, boolean flag){
        StringBuilder sb = new StringBuilder();
        if(str != null && str.length() > 1){
            String first;
            if(flag){
                first = str.substring(0, 1).toUpperCase();
            }else{
                first = str.substring(0, 1).toLowerCase();
            }

            sb.append(first).append(str.substring(1));
        }

        return sb.toString();
    }

    /**
     * 字符串拼接
     * @param sign
     * @param strs
     * @return
     */
    public static String joinStr(String sign, String... strs){
        StringBuilder sb = new StringBuilder();
        Optional<String> optional  = Arrays.stream(strs).filter(Objects::nonNull
        ).reduce((a, b) -> a + sign + b);
        optional.ifPresent(sb::append);
        return sb.toString();
    }

    /** 
     * <p>格式化文件大小</p>
     * @param size 文件大小，单位：B
     * @throws 
     * @return 
     * @author yujl@ancda.com
     */
    public static String getPrintSize(long size){
        if(size <= 0) {
            return "0KB";
        }

        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB", "BB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

}
