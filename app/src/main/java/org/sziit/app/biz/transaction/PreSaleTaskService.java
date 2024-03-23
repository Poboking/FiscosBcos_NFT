package org.sziit.app.biz.transaction;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.PreSaleTaskEntity;
import org.sziit.infrastructure.repository.impl.PreSaleTaskRepositoryImpl;

import java.util.Optional;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/18 15:52
 */
@Service
@NoArgsConstructor
public class PreSaleTaskService {
    private PreSaleTaskRepositoryImpl preSaleTaskRepository;

    @Autowired
    public PreSaleTaskService(PreSaleTaskRepositoryImpl preSaleTaskRepository) {
        this.preSaleTaskRepository = preSaleTaskRepository;
    }


    public boolean checkHasPreSale(String collectionId) {
        return preSaleTaskRepository.getOneOpt(new QueryWrapper<PreSaleTaskEntity>()
                .eq(Optional.ofNullable(collectionId).isPresent(), "collection_id", collectionId)).isPresent();
    }
}
