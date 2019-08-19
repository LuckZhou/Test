package com.mvc.util;


import org.springframework.util.StringUtils;

import java.util.UUID;

public abstract class UUIDUtil {
    public static String createUUID() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

    public static String createUUIDWithTime() {
        return createUUID() + CalendarUtil.millis();
    }
}
