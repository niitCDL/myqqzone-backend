package com.example.web.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {

    /**
     * 对元数据生成 MD5 摘要
     * @param source
     * @return
     */
    public static String md5Digest(String source){
        return DigestUtils.md2Hex(source);
    }

    /**
     * 对源数据加盐混淆后生成 MD5 摘要
     * @param source
     * @param salt 盐值
     * @return
     */
    public static String md5Digest(String source,Integer salt){
        char[] chars = source.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + salt);
        }
        String target = new String(chars);
        return DigestUtils.md2Hex(target);
    }

    public static void main(String[] args) {
        String test = md5Digest("123456",12);
        System.out.println(test);
    }
}
