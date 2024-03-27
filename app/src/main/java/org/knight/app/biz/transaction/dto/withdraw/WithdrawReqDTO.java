package org.knight.app.biz.transaction.dto.withdraw;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.knight.app.biz.transaction.dto.TransactionBaseDTO;
import org.knight.app.biz.exception.BizException;
import org.knight.infrastructure.common.CalculateUtil;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.WithdrawRecordEntity;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * DTO需要注意序列化和反序列化, 构造器得齐全, 无论有参还是无参, 以上(≧∇≦)ﾉ
 *
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/23 19:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawReqDTO extends TransactionBaseDTO {

    @NotNull
    private String settlementAccountId;

    @NotNull
    private Double amount;

    @JsonIgnore
    public static WithdrawRecordEntity quickBuild(WithdrawReqDTO reqDTO, String memberId) {
        if (reqDTO.getAmount() <= 0) {
            throw new BizException("提现金额必须大于0");
        }
        if (reqDTO.getAmount() > NftConstants.提现单笔最大金额) {
            throw new BizException("提现金额不能超过" + NftConstants.提现单笔最大金额);
        }
        if (reqDTO.getAmount() < NftConstants.提现单笔最小金额) {
            throw new BizException("提现金额不能小于" + NftConstants.提现单笔最小金额);
        }
        return WithdrawRecordEntity.builder()
                .amount(reqDTO.getAmount())
                .settlementAccountId(reqDTO.getSettlementAccountId())
                .memberId(memberId)
                .state(NftConstants.提现记录状态_已提现)
                .submitTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)))
                .dealTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)))
                .handlingFee(0d)
                .toTheAccount(reqDTO.amount)
                .build();
    }

    @JsonIgnore
    public static WithdrawRecordEntity defaultBuild(WithdrawReqDTO reqDTO, String memberId) {
        if (reqDTO.getAmount() <= 0) {
            throw new BizException("提现金额必须大于0");
        }
        if (reqDTO.getAmount() > NftConstants.提现单笔最大金额) {
            throw new BizException("提现金额不能超过" + NftConstants.提现单笔最大金额);
        }
        if (reqDTO.getAmount() < NftConstants.提现单笔最小金额) {
            throw new BizException("提现金额不能小于" + NftConstants.提现单笔最小金额);
        }
        Double handlingFee = CalculateUtil.calculateHandlingFee(reqDTO.getAmount());
        return WithdrawRecordEntity.builder()
                .amount(reqDTO.getAmount())
                .settlementAccountId(reqDTO.getSettlementAccountId())
                .memberId(memberId)
                .state(NftConstants.提现记录状态_审核中)
                .submitTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)))
                .handlingFee(handlingFee)
                .toTheAccount(reqDTO.amount - handlingFee)
                .build();
    }
}
