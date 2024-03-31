package org.knight.presentation.admin;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.knight.app.biz.transaction.TransactionService;
import org.knight.app.biz.transaction.dto.trade.TradeStatisticDayRespDTO;
import org.knight.app.biz.transaction.dto.trade.TradeStatisticRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 15:39
 */
@RestController
@RequestMapping("/back/tradeStatistic/")
public class TradeStatisticController {

    private final TransactionService transactionService;

    @Autowired
    public TradeStatisticController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("getTradeStatisticData")
    @ValidationStatusCode(code = "400")
    public TradeStatisticRespDTO getTradeStatisticData(@Valid @RequestParam(name = "bizMode") String bizMode) {
        return transactionService.getTradeStatisticData(bizMode);
    }

    @GetMapping("findEverydayTradeData")
    @ValidationStatusCode(code = "400")
    @Operation(description = "查询每日交易数据 -  startDate格式：yyyy-MM-dd  endDate格式：yyyy-MM-dd 类型为String")
    public List<TradeStatisticDayRespDTO> findEverydayTradeData(
            @Valid @RequestParam(name = "bizMode") String bizMode,
            @Valid @RequestParam(name = "startDate", required = false) String startDate,
            @Valid @RequestParam(name = "endDate", required = false) String endDate) {
        return transactionService.getEverydayTradeData(bizMode, startDate, endDate);
    }


}
