package org.knight.presentation.member.transaction;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.knight.app.biz.convert.transaction.SettlementAccountConvert;
import org.knight.app.biz.convert.transaction.WithdrawConvert;
import org.knight.app.biz.transaction.dto.withdraw.WithdrawReqDTO;
import org.knight.app.biz.transaction.dto.withdraw.WithdrawRespDTO;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.WithdrawRecordEntity;
import org.knight.infrastructure.repository.impl.SettlementAccountRepositoryImpl;
import org.knight.infrastructure.repository.impl.WithdrawRecordRepositoryImpl;
import org.knight.presentation.utils.StpUserUtil;

import java.util.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/20 22:41
 */
@Log4j2
@RestController
@RequestMapping("/withdraw/")
public class WithdrawController {

    private final WithdrawRecordRepositoryImpl withdrawRepository;

    private final SettlementAccountRepositoryImpl settlementRepository;

    @Autowired
    public WithdrawController(WithdrawRecordRepositoryImpl withdrawRepository, SettlementAccountRepositoryImpl settlementRepository) {
        this.withdrawRepository = withdrawRepository;
        this.settlementRepository = settlementRepository;
    }


    /**
     * settlementAccountType: wechat, alipay, bank
     * state: 1(审核中), 2(已提现), 3(已驳回)
     */
    @GetMapping("findByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<WithdrawRespDTO> findByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "settlementAccountType", required = false) String settlementAccountType) {

        Page<WithdrawRecordEntity> page = withdrawRepository.page(new Page<>(current, pageSize), new QueryWrapper<WithdrawRecordEntity>()
                .eq("member_id", StpUserUtil.getLoginIdAsString())
                .eq(Optional.ofNullable(state).isPresent(), "state", state)
                .eq(Optional.ofNullable(settlementAccountType).isPresent(), "settlement_account_type", settlementAccountType));

        List<WithdrawRespDTO> resultList = new ArrayList<>();
        page.getRecords().forEach(item -> {
            WithdrawRespDTO respDTO = WithdrawConvert.INSTANCE.convertToRespDTO(item);
            respDTO.setSettlementAccount(SettlementAccountConvert.INSTANCE.convertToRespDTO(settlementRepository.getById(item.getSettlementAccountId())));
            resultList.add(respDTO);
        });
        return PageResult.convertFor(page, page.getSize(), resultList);
    }

    // TODO: 2024/3/23 15:52 待完善提现审核机制
    @PostMapping("withdraw")
    @ValidationStatusCode(code = "400")
    public Map<String, Boolean> withdraw(@Valid @RequestBody WithdrawReqDTO reqDTO) {
//        if (reqDTO.getAmount() <= 0) {
//            return Map.of("result", false);
//        }
//        if (reqDTO.getAmount() > 100000) {
//            return Map.of("result", false);
//        }
        if (Objects.isNull(reqDTO.getAmount())) {
            return Map.of("result", false);
        }
        if (Objects.isNull(reqDTO.getSettlementAccountId())) {
            return Map.of("result", false);
        }
        if (settlementRepository.getById(reqDTO.getSettlementAccountId()) == null) {
            return Map.of("result", false);
        }
        String loginId = StpUserUtil.getLoginIdAsString();
        return Map.of("result", withdrawRepository.save(WithdrawReqDTO.defaultBuild(reqDTO, loginId)));
    }
}
