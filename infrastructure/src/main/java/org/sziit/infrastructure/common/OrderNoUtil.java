package org.sziit.infrastructure.common;

import cn.hutool.core.lang.Snowflake;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/23 20:30
 */
public class OrderNoUtil {

    private static Snowflake snowflake = new Snowflake(1, 1);

    public static String generateOrderNo() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String formattedDateTime = now.format(formatter);
        return String.valueOf(snowflake.nextId()) + formattedDateTime;
    }
}
