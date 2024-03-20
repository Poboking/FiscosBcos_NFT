package org.sziit.app.biz.transaction;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.convert.transaction.CollectionGiveRecordConvert;
import org.sziit.app.biz.convert.transaction.PayOrderConvert;
import org.sziit.app.biz.transaction.dto.giverecord.CollectionGiveRecordRespDTO;
import org.sziit.app.biz.transaction.dto.order.PayOrderRespDTO;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.CollectionGiveRecordEntity;
import org.sziit.infrastructure.dao.domain.PayOrderEntity;
import org.sziit.infrastructure.repository.impl.CollectionGiveRecordRepositoryImpl;
import org.sziit.infrastructure.repository.impl.PayOrderRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 23:40
 */
@Service
@AllArgsConstructor
public class TransactionService {
    @Autowired
    private PayOrderRepositoryImpl payOrderRepository;
    @Autowired
    private CollectionGiveRecordRepositoryImpl collectionGiveRecordRepository;

    public PageResult<PayOrderRespDTO> getMyPayOrder(long current, long pageSize, String status, String memberId) {
        IPage<PayOrderEntity> pageList = null;
        if (status == null) {
            pageList = payOrderRepository.getPayOrderPageListByMemberId(current, pageSize, memberId);
        } else {
            pageList = payOrderRepository.getPayOrderPageListByMemberIdAndStatus(current, pageSize, memberId, status);
        }
        List<PayOrderRespDTO> recordList = new ArrayList<>();
        pageList.getRecords().forEach(payOrderEntity -> {
            recordList.add(PayOrderConvert.INSTANCE.convertToDTO(payOrderEntity));
        });
        return PageResult.convertFor(pageList, pageSize, recordList);
    }

    public PageResult<CollectionGiveRecordRespDTO> getMyGiveRecord(long current, long pageSize, String memberId, String giveDirection) {
        IPage<CollectionGiveRecordEntity> pageList = null;
        if (giveDirection.equals("from")) {
            pageList = collectionGiveRecordRepository.getPageListByGiveFromId(current, pageSize, memberId);
        }
        if (giveDirection.equals("to")) {
            pageList = collectionGiveRecordRepository.getPageListByGiveToId(current, pageSize, memberId);
        }
        else {
            pageList = collectionGiveRecordRepository.getPageListByGiveToIdOrGiveFormId(current, pageSize, memberId, memberId);
        }
        List<CollectionGiveRecordRespDTO> recordList = new ArrayList<>();
        pageList.getRecords().forEach(collectionGiveRecordEntity -> {
            recordList.add(CollectionGiveRecordConvert.INSTANCE.convertToDTO(collectionGiveRecordEntity));
        });

        return PageResult.convertFor(pageList, pageSize, recordList);
    }
}
