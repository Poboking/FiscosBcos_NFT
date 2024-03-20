package org.sziit.presentation.member.transaction;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sziit.app.biz.transaction.SettlementAccountService;
import org.sziit.app.biz.transaction.dto.settlement.SettlementAccountAddReqDTO;
import org.sziit.app.biz.transaction.dto.settlement.SettlementAccountRespDTO;
import org.sziit.app.biz.transaction.dto.settlement.SettlementAccountUpdateReqDTO;

import java.util.List;
import java.util.Map;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/20 22:01
 */
@Log4j2
@RestController
@RequestMapping("/settlementAccount/")
@Tag(name = "SettlementAccountController", description = "SETTLEMENT_ACCOUNT_CONTROLLER")
@Schema(description = "Api 待实现")
public class SettlementAccountController {
    private final SettlementAccountService accountService;

    // TODO: 2024/3/20 22:01 以下方法待实现
    @Autowired
    public SettlementAccountController(SettlementAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("findAll")
    @ValidationStatusCode(code = "400")
    @Operation(description = "Api 待实现")
    public List<SettlementAccountRespDTO> findAll() {
        return null;
    }

    @PostMapping("add")
    @ValidationStatusCode(code = "400")
    @Operation(description = "Api 待实现")
    public Map<String , Boolean> add(@Valid @RequestBody SettlementAccountAddReqDTO reqDTO) {
        return null;
    }

    @PostMapping("updateActivatedFlag")
    @ValidationStatusCode(code = "400")
    @Operation(description = "Api 待实现")
    public Map<String , Boolean> updateActivatedFlag(@Valid @RequestBody SettlementAccountUpdateReqDTO reqDTO) {
        return null;
    }


//    @GetMapping("findAll")
//    @ValidationStatusCode(code = "400")
//    public List<SettlementAccountRespDTO> findAll() {
//        return accountService.findAll();
//    }
//
//    @PostMapping("add")
//    @ValidationStatusCode(code = "400")
//    public Map<String , Boolean> add(@Valid @RequestBody SettlementAccountAddReqDTO reqDTO) {
//        return Collections.singletonMap("result", accountService.add(reqDTO));
//    }
//
//    @PostMapping("updateActivatedFlag")
//    @ValidationStatusCode(code = "400")
//    public Map<String , Boolean> updateActivatedFlag(@Valid @RequestBody SettlementAccountUpdateReqDTO reqDTO) {
//        return Collections.singletonMap("result", accountService.updateActivatedFlag(reqDTO.getAccountId(), reqDTO.getActivated()));
//    }


}
