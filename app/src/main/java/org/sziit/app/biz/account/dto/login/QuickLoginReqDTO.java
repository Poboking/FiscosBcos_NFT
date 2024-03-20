package org.sziit.app.biz.account.dto.login;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/19 21:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuickLoginReqDTO extends LoginBaseDTO{
    @NotNull
    @Length(min = 11, max = 11)
    private String mobile;
}
