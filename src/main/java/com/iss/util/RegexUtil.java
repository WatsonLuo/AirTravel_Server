package com.iss.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexUtil {
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isEmailLegal(String str) throws PatternSyntaxException {
        String regExp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isIDLegal(String str) throws PatternSyntaxException {
        boolean flag=false;//正则匹配身份证格式,缺陷是未检验日期的正确性

        Pattern p=Pattern.compile("(^[1-8][0-7]{2}\\d{3}([12]\\d{3})(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}([0-9Xx])$)");
        Matcher m=p.matcher(str);
        //匹配最后一位检验码是否正确
        int index[]= {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        //检验码对应规则，第三位实际上应该是x，这个地方用100但是实际上检验时不会用到
        int check[]= {1,0,100,9,8,7,6,5,4,3,2};
        if(m.matches())	{
            int sum=0;
            for(int i=0;i<17;i++)	sum+=index[i]*(str.charAt(i)-'0');
            sum%=11;
            if(sum==2 && ( str.charAt(17)=='x'||str.charAt(17)=='X' ) )	flag=true;
            else if(check[sum]==(str.charAt(17)-'0'))	flag=true;
        }
        if(flag) return true;
        else return false;

    }



}
