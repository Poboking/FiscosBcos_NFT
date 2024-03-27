package org.knight.infrastructure.common;

import lombok.Data;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/19 21:19
 */
@Data
public class ToBeRealizedVO {
    private static final String API_STATE = "The Method is not realized yet!";

    public static ToBeRealizedVO build() {
        return new ToBeRealizedVO();
    }
}
