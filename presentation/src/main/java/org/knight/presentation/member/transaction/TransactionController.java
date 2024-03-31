package org.knight.presentation.member.transaction;

import cn.hutool.core.text.CharSequenceUtil;
import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.transaction.PreSaleTaskService;
import org.knight.app.biz.transaction.TransactionService;
import org.knight.app.biz.transaction.dto.giverecord.CollectionGiveRecordRespDTO;
import org.knight.app.biz.transaction.dto.order.PayOrderRespDTO;
import org.knight.infrastructure.common.PageResult;
import org.knight.presentation.utils.StpUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 19:58
 */
//@CheckUserLogin
@Log4j2
@RestController
@RequestMapping("/transaction/")
@Tag(name = "TransactionController", description = "TRANSACTION_CONTROLLER")
public class TransactionController {
    private TransactionService transactionService;
    private final PreSaleTaskService preSaleTaskService;

    @Autowired
    public TransactionController(TransactionService transactionService, PreSaleTaskService preSaleTaskService) {
        this.transactionService = transactionService;
        this.preSaleTaskService = preSaleTaskService;
    }


    @GetMapping("checkHasPreSale")
    @ValidationStatusCode(code = "400")
    public boolean checkHasPreSale(
            @RequestParam(name = "collectionId") String collectionId) {
        String memberId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("User({}): checkHasPreSale", memberId));
        // TODO: 2024/3/31 19:58 待实现
        return preSaleTaskService.checkHasPreSale(collectionId);
    }


    @PostMapping("latestCollectionCreateOrder")
    @ValidationStatusCode(code = "400")
    public Map<String, String> latestCollectionCreateOrder(@RequestParam(name = "collectionId") @NotNull String collectionId) {
        String memberId = StpUserUtil.getLoginIdAsString();
        // TODO: 2024/3/31  待检查
        return transactionService.latestCollectionCreateOrder(collectionId, memberId);
    }

    @PostMapping("confirmPay")
    @ValidationStatusCode(code = "500")
    public Map<String, Boolean> confirmPay(@RequestParam(name = "orderId") @NotNull String orderId) {
        String memberId = StpUserUtil.getLoginIdAsString();
        return transactionService.confirmPay(orderId, memberId);
    }

    @GetMapping("findMyPayOrderByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<PayOrderRespDTO> findMyPayOrderByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "status", required = false) String status) {
        String memberId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("User({}): findMyPayOrderByPage", memberId));
        return transactionService.getMyPayOrder(current, pageSize, status, memberId);
    }


    @GetMapping("findMyGiveRecordByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<CollectionGiveRecordRespDTO> findMyGiveRecordByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "giveDirection", required = false) String giveDirection) {
        String memberId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("User({}): findMyGiveRecordByPage", memberId));
        return transactionService.getMyGiveRecord(current, pageSize, memberId, giveDirection);
    }

    @PostMapping("resaleCollectionCreateOrder")
    @ValidationStatusCode(code = "400")
    public Map<String, String> resaleCollectionCreateOrder(@RequestParam(name = "resaleCollectionId") @NotNull String resaleCollectionId) {
        String memberId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("User({}): resaleCollectionCreateOrder", memberId));
        return transactionService.resaleCollectionCreateOrder(resaleCollectionId, memberId);
    }
}
