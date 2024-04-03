package org.knight.app.biz.transaction.task;

import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.transaction.TransactionService;
import org.knight.app.biz.transaction.bo.OrderBO;
import org.knight.infrastructure.common.NftConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/3 21:04
 */
@Component
public class OrderTask {

    private final TransactionService transactionService;

    @Autowired
    public OrderTask(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Scheduled(fixedRate = 1000 * 60 * 30)
    public void cancelExpiredOrder() {
        List<OrderBO> expiredOrders = transactionService.findExpiredOrders();
        for (OrderBO order : expiredOrders) {
            if (Objects.isNull(order))
                continue;
            transactionService.cancelOrder(order.getId());
        }
    }
}
