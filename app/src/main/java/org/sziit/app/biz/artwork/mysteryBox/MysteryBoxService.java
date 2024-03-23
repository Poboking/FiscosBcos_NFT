package org.sziit.app.biz.artwork.mysteryBox;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.artwork.dto.mysteryBox.MysteryBoxRespDTO;
import org.sziit.app.biz.convert.artwork.MysteryBoxConvert;
import org.sziit.infrastructure.common.NftConstants;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.dao.domain.CreatorEntity;
import org.sziit.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.sziit.infrastructure.repository.impl.CreatorRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/19 20:46
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class MysteryBoxService {
    @Autowired
    private CollectionRepositoryImpl collectionRepository;
    @Autowired
    private CreatorRepositoryImpl creatorRepository;

    public PageResult<MysteryBoxRespDTO> getPageList(long current, long pageSize) {
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageListByCommodityType(current, pageSize, NftConstants.商品类型_盲盒);
        List<MysteryBoxRespDTO> resultList = new ArrayList<>();
        pageEntity.getRecords().forEach(bean -> {
            CreatorEntity creator = creatorRepository.getById(bean.getCreatorId());
            if (creator == null) {
                return;
            }
            bean.setCreatorName(creator.getName());
            bean.setCreatorAvatar(creator.getAvatar());
            resultList.add(MysteryBoxConvert.INSTANCE.convertToRespDTO(bean));
        });
        return PageResult.convertFor(pageEntity, pageSize, resultList);
    }

    public PageResult<MysteryBoxRespDTO> getPageListByCreatorId(long current, long pageSize, String creatorId) {
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageListByCreatorIdAndCommodityType(current, pageSize, creatorId, NftConstants.商品类型_盲盒);
        List<MysteryBoxRespDTO> resultList = new ArrayList<>();
        pageEntity.getRecords().forEach(bean -> {
            CreatorEntity creator = creatorRepository.getById(creatorId);
            if (creator == null) {
                return;
            }
            bean.setCreatorName(creator.getName());
            bean.setCreatorAvatar(creator.getAvatar());
            resultList.add(MysteryBoxConvert.INSTANCE.convertToRespDTO(bean));
        });
        return PageResult.convertFor(pageEntity, pageSize, resultList);
    }
}
