package org.knight.presentation.member.transaction;

import cn.hutool.core.text.CharSequenceUtil;
import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.transaction.PreSaleTaskService;
import org.knight.app.biz.transaction.TransactionService;
import org.knight.app.biz.transaction.dto.giverecord.CollectionGiveRecordRespDTO;
import org.knight.app.biz.transaction.dto.member.ReceiverInfoRespDTO;
import org.knight.app.biz.transaction.dto.order.PayOrderRespDTO;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.PageResult;
import org.knight.presentation.utils.StpUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        Timestamp now = Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT));
        return transactionService.latestCollectionCreateOrder(collectionId, memberId, now);
    }

    @PostMapping("resaleCollectionCreateOrder")
    @ValidationStatusCode(code = "400")
    public Map<String, String> resaleCollectionCreateOrder(@RequestParam(name = "resaleCollectionId") @NotNull String resaleCollectionId,
                                                           @RequestParam(name = "collectionSerialNumber") @NotNull int collectionSerialNumber) {
        String memberId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("User({}): resaleCollectionCreateOrder {}", memberId, collectionSerialNumber));
        Timestamp now = Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT));
        return transactionService.resaleCollectionCreateOrder(resaleCollectionId, collectionSerialNumber, memberId, now);
    }

    @PostMapping("confirmPay")
    @ValidationStatusCode(code = "500")
    public Map<String, Boolean> confirmPay(@RequestParam(name = "orderId") @NotNull String orderId) {
        String memberId = StpUserUtil.getLoginIdAsString();
        return transactionService.confirmPay(orderId, memberId);
    }

    @PostMapping("cancelOrder")
    @ValidationStatusCode(code = "500")
    public Map<String, Boolean> cancelOrder(@RequestParam(name = "orderId") @NotNull String orderId) {
        String memberId = StpUserUtil.getLoginIdAsString();
        return Map.of("result", transactionService.cancelOrder(orderId, memberId));
    }


    @PostMapping("collectionResale")
    @ValidationStatusCode(code = "500")
    public Map<String, Boolean> collectionResale(
            @RequestParam(name = "resalePrice") long resalePrice,
            @RequestParam(name = "holdCollectionId") String holdCollectionId) throws Exception {
        String memberId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("User({}): collectionResale", memberId));
        return Map.of("result", transactionService.collectionResale(memberId, holdCollectionId, resalePrice));
    }


    @PostMapping("cancelResale")
    @ValidationStatusCode(code = "500")
    public Map<String, Boolean> cancelResale(@RequestParam(name = "resaleCollectionId") String resaleCollectionId) {
        String memberId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("User({}): cancelResale", memberId));
        return Map.of("result", transactionService.cancelResale(memberId, resaleCollectionId));
    }


    @PostMapping("collectionGive")
    @ValidationStatusCode(code = "500")
    public Map<String, Boolean> collectionGive(
            @RequestParam(name = "issuedCollectionId") @NotNull String issuedCollectionId,
            @RequestParam(name = "giveToAccount") @NotNull String giveToAccount) {
        String memberId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("User({}): collectionGive", memberId));
        return Map.of("result", transactionService.collectionGive(memberId, issuedCollectionId, giveToAccount));
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

    @GetMapping("findMyPayOrderDetail")
    @ValidationStatusCode(code = "500")
    public PayOrderRespDTO findMyPayOrderDetail(@RequestParam(name = "orderId") @NotNull String orderId) {
        String memberId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("User({}): findMyPayOrderDetail", memberId));
        return transactionService.getMyPayOrderDetail(orderId, memberId);
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


    @GetMapping("getCollectionReceiverInfo")
    @ValidationStatusCode(code = "400")
    public ReceiverInfoRespDTO getCollectionReceiverInfo(
            @RequestParam(name = "giveToAccount") String giveToAccount) {
        String memberId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("User({}): getCollectionReceiverInfo", memberId));
        return transactionService.getCollectionReceiverInfo(giveToAccount);
    }

}
