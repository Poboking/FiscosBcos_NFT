package org.sziit.app.biz.vo.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 21:51
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountVo {

    private String nickName;

    private String realName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date bindRealNameTime;

    private String avatar;

    private String mobile;

    private String blockChainAddr;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date latelyLoginTime;

}