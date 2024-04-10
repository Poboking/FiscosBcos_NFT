package org.knight.infrastructure.common;

import org.fisco.bcos.sdk.utils.Numeric;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/10 20:47
 */
public class SolidityAddressGenerator {
    private SolidityAddressGenerator() {}

    // 将任意字符串转换为 Solidity 地址类型的字符串
    public static String generateAddress(String inputString) {
        try {
            // 使用 SHA-256 哈希函数生成 32 字节哈希值
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(inputString.getBytes());

            // 将哈希值转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // 截取前 40 位，确保长度为 40
            String hexHash = hexString.toString();
            hexHash = hexHash.substring(0, 40);

            // 添加 0x 前缀
            return "0x" + hexHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
