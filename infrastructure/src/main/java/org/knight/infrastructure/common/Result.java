package org.knight.infrastructure.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 18:13
 */
@Data
@Builder
@AllArgsConstructor
@SuppressWarnings("all")
public class Result implements Serializable {
    private String code;

    private String msg;

    private Object data;
}
