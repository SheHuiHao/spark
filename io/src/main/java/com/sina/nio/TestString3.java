package com.sina.nio;

import java.util.HashMap;
import java.util.Set;

public class TestString3 {
    //统计字符串中出现的次数
    public static void main(String[] args) {
        String str="4723984793278932fnrhjkfh7493284323243243432";
        char[] chars = str.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if(map.containsKey(aChar)){
                map.put(aChar,map.get(aChar)+1);
            }else {
                map.put(aChar,1);
            }
        }
        for (Character ss : map.keySet()) {
            System.out.println(ss+"  "+map.get(ss));
        }
    }
}
