package org.knight.app.biz.transaction.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/3 21:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderBO {
    private String id;

    private LocalDateTime createTime;

    private String status;
}
