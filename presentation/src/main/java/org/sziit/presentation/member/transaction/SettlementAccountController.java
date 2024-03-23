package org.sziit.presentation.member.transaction;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sziit.app.biz.transaction.SettlementAccountService;
import org.sziit.app.biz.transaction.dto.settlement.SettlementAccountAddReqDTO;
import org.sziit.app.biz.transaction.dto.settlement.SettlementAccountRespDTO;
import org.sziit.app.biz.transaction.dto.settlement.SettlementAccountUpdateReqDTO;
import org.sziit.presentation.exception.BadRequestException;
import org.sziit.presentation.utils.StpUserUtil;

import java.util.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/20 22:01
 */
@Log4j2
@RestController
@RequestMapping("/settlementAccount/")
@Tag(name = "SettlementAccountController", description = "SETTLEMENT_ACCOUNT_CONTROLLER")
public class SettlementAccountController {
    private final SettlementAccountService accountService;

    @Autowired
    public SettlementAccountController(SettlementAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("findAll")
    @ValidationStatusCode(code = "400")
    public List<SettlementAccountRespDTO> findAll(@Valid @RequestParam(name = "activated", required = false) Boolean activated) {
        String loginId = StpUserUtil.getLoginIdAsString();
        if (Objects.isNull(activated))
            accountService.findAll(loginId);
        return accountService.findAll(loginId, activated);
    }


    @PostMapping("add")
    @ValidationStatusCode(code = "400")
    public Map<String, Boolean> add(@Valid @RequestBody SettlementAccountAddReqDTO reqDTO) {
        String[] types = {"wechat", "alipay", "bank"};
        if (reqDTO.getType().isEmpty()) {
            throw new BadRequestException("type不能为空");
        }
        if (!Arrays.asList(types).contains(reqDTO.getType())) {
            throw new BadRequestException("type不合法");
        }
        if (reqDTO.getType().equals("wechat") || reqDTO.getType().equals("alipay") && (reqDTO.getAccount().isEmpty())) {
            throw new BadRequestException("account不能为空");
        }
        if (reqDTO.getType().equals("bank") && (reqDTO.getBankName().isEmpty() || reqDTO.getCardNumber() == null)) {
            throw new BadRequestException("Bank参数不能为空");
        }
        String loginId = StpUserUtil.getLoginIdAsString();
        return Collections.singletonMap("result", accountService.add(loginId, reqDTO));
    }

    @PostMapping("updateActivatedFlag")
    @ValidationStatusCode(code = "400")
    public Map<String, Boolean> updateActivatedFlag(@Valid @RequestBody SettlementAccountUpdateReqDTO reqDTO) {
        String loginId = StpUserUtil.getLoginIdAsString();
        return Collections.singletonMap("result", accountService.updateActivatedFlag(loginId, reqDTO.getAccountId(), reqDTO.getActivated()));
    }
}
