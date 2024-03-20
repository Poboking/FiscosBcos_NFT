package org.sziit.presentation.member.transaction;

import cn.hutool.core.text.CharSequenceUtil;
import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sziit.app.biz.transaction.PreSaleTaskService;
import org.sziit.app.biz.transaction.TransactionService;
import org.sziit.app.biz.transaction.dto.giverecord.CollectionGiveRecordRespDTO;
import org.sziit.app.biz.transaction.dto.order.PayOrderRespDTO;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.presentation.utils.StpUserUtil;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 19:58
 */
//@CheckUserLogin
@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/transaction/")
@Tag(name = "TransactionController", description = "TRANSACTION_CONTROLLER")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PreSaleTaskService preSaleTaskService;



    @GetMapping("checkHasPreSale")
    @ValidationStatusCode(code = "400")
    public boolean checkHasPreSale(
            @RequestParam(name = "collectionId") String collectionId) {
        String memberId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("User({}): checkHasPreSale", memberId));
        return preSaleTaskService.checkHasPreSale(collectionId);
    }


    // TODO: 2024/3/18 15:52 以下方法待实现 生成预售订单或订单
    @PostMapping("latestCollectionCreateOrder")
    @ValidationStatusCode(code = "400")
    @Operation(description = "Api 待实现")
    public void latestCollectionCreateOrder(
            @RequestBody String memberId,
            @RequestParam(name = "collectionId") String collectionId) {
        log.info(CharSequenceUtil.format("collectionId({}), memberId({}): latestCollectionCreateOrder", collectionId, memberId));
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
}