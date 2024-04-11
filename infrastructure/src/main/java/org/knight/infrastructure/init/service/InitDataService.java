package org.knight.infrastructure.init.service;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.knight.infrastructure.common.IdUtils;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.domain.IssuedCollectionEntity;
import org.knight.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.IssuedCollectionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/11 9:21
 */
@Component
@Log4j2
@AllArgsConstructor
@SuppressWarnings("all")
public class InitDataService {
    @Autowired
    private final CollectionRepositoryImpl collectionRepo;

    @Autowired
    private final IssuedCollectionRepositoryImpl issuedCollectionRepo;

    public void initCollectionStock() {
        collectionRepo.list().stream().map(item -> {
            if (item.getStock() > item.getQuantity()) {
                item.setStock(item.getQuantity());
            }
            return item;
        }).forEach(item -> {
            collectionRepo.updateById(item);
        });
    }

    public void initIssuedCollection() {
        List<CollectionEntity> list = collectionRepo.list();
        list.stream().forEach(collectionEntity -> {
            IntStream.range(1,collectionEntity.getQuantity() + 1).mapToObj(serialNumber ->{
                String issuedId = issuedCollectionRepo.getIssuedIdByCollectionIdAndSerialNumber(collectionEntity.getId(), serialNumber);
                if (Objects.isNull(issuedId) || CharSequenceUtil.isBlank(issuedId)){
                    return IssuedCollectionEntity.builder()
                            .collectionId(collectionEntity.getId())
                            .collectionSerialNumber(serialNumber)
                            .id(IdUtils.snowFlakeId())
                            .issueTime(collectionEntity.getCreateTime())
                            .syncChainTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)))
                            .uniqueId(IdUtils.uuid())
                            .deletedFlag(false)
                            .build();
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList()).forEach(issuedCollectionEntity -> {
                issuedCollectionRepo.saveOrUpdate(issuedCollectionEntity);
                log.info("initIssuedCollection: {} 插入数据成功", issuedCollectionEntity.getId());
            });
        });
    }
}
