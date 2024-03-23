package org.sziit.app.biz.account.dto.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/16 15:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRespDTO extends AccountBaseDTO {

    private String nickName;

    private String realName;

    private String avatar;

    private String mobile;

    private String blockChainAddr;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date bindRealNameTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date latelyLoginTime;

}
