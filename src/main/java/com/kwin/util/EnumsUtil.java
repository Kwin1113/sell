package com.kwin.util;

import com.kwin.enums.CodeEnums;

public class EnumsUtil {

    public static <T extends CodeEnums> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
