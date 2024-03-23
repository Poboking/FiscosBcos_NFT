package org.sziit.infrastructure.common;

import cn.hutool.core.lang.Snowflake;

import java.util.UUID;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/16 16:39
 */
public class IdUtils {
    private static final Snowflake snowflake = cn.hutool.core.util.IdUtil.getSnowflake(0, 0);

    /**
     * Snowflake ID
     */
    public static String snowFlakeId() {
        return snowflake.nextId() + "";
    }

    /**
     * UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
