package org.knight.presentation.admin;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.exception.param.IllegalDateException;
import org.knight.app.biz.transaction.TransactionService;
import org.knight.app.biz.transaction.dto.order.PayOrderRespDTO;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/15 15:56
 */
@Log4j2
@RestController
@RequestMapping("/back/payOrder/")
public class BackPayController {
    private final TransactionService transactionService;

    @Autowired
    public BackPayController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("findPayOrderByPage")
    @ValidationStatusCode(code = "400")
    @Operation(description = "查询指定日期用户订单 -  startDate格式：yyyy-MM-dd  endDate格式：yyyy-MM-dd 类型为String")
    public PageResult<PayOrderRespDTO> findPayOrderByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "memberMobile", required = false) String memberMobile,
            @RequestParam(name = "collectionName", required = false) String collectionName,
            @RequestParam(name = "bizMode", required = false) String bizMode,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate) {
        LocalDateTime start = null, end = null;
        try {
            if (startDate != null && endDate != null) {
                start = LocalDateTime.parse(startDate, NftConstants.SIMPLE_DATE_FORMAT);
                end = LocalDateTime.parse(endDate, NftConstants.SIMPLE_DATE_FORMAT);
            }
        }catch (Exception e){
            throw new IllegalDateException("日期格式错误:" + e);
        }
        return transactionService.findPayOrderByPage(current, pageSize, memberMobile, collectionName, bizMode, state, start, end);
    }
}
