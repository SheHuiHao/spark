package com.sina.nio;

public class TestString2 {
    public static void main(String[] args) {
        String str="r7489rfhsefjksdggdkhg  higrglhdgnskljgskhgkhrgskl7r584357438558964343fgnd   iutore8utoue8t58o676t9otrejltlrejre[p][p['rlg;dgk;fdg";
        String ss = ss(str);
        System.out.println(ss);
    }
    public static String ss(String str){
        int zimu=0;  //字母计数器
        int shuzi=0;  //数字计数器
        int t=0;     //空格计数器
        int qita=0;   //其他计数器
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if((aChar>'A'&&aChar<'Z')||(aChar>'a'&&aChar<'z')) zimu++;
            else if(aChar>'0'&&aChar<'9') shuzi++;
            else if(aChar==' ') t++;
            else qita++;
        }
        return "字母"+zimu+"  "+"数字"+shuzi+"  "+"空格"+t+"  "+"其他"+qita;
    }
}
