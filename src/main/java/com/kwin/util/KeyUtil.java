package com.kwin.util;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一主键
     * @return 格式：时间+6位随机数
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
