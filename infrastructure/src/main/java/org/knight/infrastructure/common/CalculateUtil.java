package org.knight.infrastructure.common;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/23 19:20
 */
public class CalculateUtil {

    public static Double calculateHandlingFee(Double amount) {
        double feeRate = 0.001;
        double minFee = 0.01;
        double fee = amount * feeRate;
        if (fee < minFee) {
            return minFee;
        }
        return fee;
    }
}
