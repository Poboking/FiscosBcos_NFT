package org.knight.infrastructure.fisco.service;

import cn.hutool.core.lang.UUID;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/25 23:23
 */

public class NFTService {

    public static String getNFTHash(String nftId) {
        return UUID.randomUUID().toString();
    }

    public static String getNFTUniqueId(String nftId) {
        return UUID.randomUUID().toString();
    }
}
