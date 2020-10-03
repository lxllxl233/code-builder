package com.woqiyounai.util;

public class CodeUtils {

    //下划线转驼峰命名法
    public static String toHumpClass(String str){
        String[] ss = str.split("_");
        str = "";
        for (String s : ss) {
            str+=(s.substring(0, 1).toUpperCase() + s.substring(1));
        }
        return str;
    }

    public static String toHumpName(String str){
        String[] ss = str.split("_");
        str = "";
        for (int i = 1; i < ss.length; i++) {
            str+=(ss[i].substring(0, 1).toUpperCase() + ss[i].substring(1));
        }
        str = ss[0]+str;
        return str;
    }
}
