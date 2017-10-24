package com.baiying.hu.util;

import java.util.Random;

/**
 * Created by jmx
 * 2017/10/1.
 */
public class InviteUtils {
    //生成十位数奖品兑换码
    public static String getInviteCode() throws Exception {
        int count = 15;
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < count; i++) {
            int d = r.nextInt(62);
            sb.append(str.charAt(d));
        }
        return sb.toString();
    }
}
