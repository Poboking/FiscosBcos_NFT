package org.knight.app.biz.account.dto.account.background;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 15:06
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BackgroundAccountRespDTO {

    private String id;

    private String userName;

    private String state;

    private String stateName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date registeredTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date latelyLoginTime;

    private String googleSecretKey;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date googleAuthBindTime;

    private Boolean superAdminFlag;
}
