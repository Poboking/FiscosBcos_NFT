package org.knight.infrastructure.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/23 20:30
 */
public class OrderNoUtil {

    public static String generateOrderNo() {
        StringBuilder orderNumber = new StringBuilder();

        // 获取当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = dateFormat.format(new Date());
        orderNumber.append(currentTime);

        // 生成随机数
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            orderNumber.append(random.nextInt(10));
        }

        return orderNumber.toString();
    }
}
