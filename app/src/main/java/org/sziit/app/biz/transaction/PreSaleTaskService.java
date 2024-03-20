package org.sziit.app.biz.transaction;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.PreSaleTaskEntity;
import org.sziit.infrastructure.repository.impl.PreSaleTaskRepositoryImpl;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/18 15:52
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class PreSaleTaskService {
    @Autowired
    private PreSaleTaskRepositoryImpl preSaleTaskRepository;


    public boolean checkHasPreSale(String collectionId) {
        return preSaleTaskRepository.getOneOpt(new QueryWrapper<PreSaleTaskEntity>()
                .eq("collection_id", collectionId)).isPresent();
    }
}
