package org.knight.infrastructure.common;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/18 21:46
 */


public class NicknameUtils {
    private static final String PREFIX = "藏家_";
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final Random random = new SecureRandom();

    public static String generate() {
        StringBuilder sb = new StringBuilder(PREFIX);
        for (int i = 0; i < 9; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

}

