package org.knight.app.biz.artwork.mysteryBox;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.knight.app.biz.artwork.dto.mysteryBox.MysteryBoxRespDTO;
import org.knight.app.biz.convert.artwork.MysteryBoxConvert;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.domain.CreatorEntity;
import org.knight.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.CreatorRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        PageInfo<CollectionEntity> pageEntity = collectionRepository.getPageListByCommodityType(current, pageSize, NftConstants.商品类型_盲盒);
        List<MysteryBoxRespDTO> resultList = new ArrayList<>();
        pageEntity.getList().forEach(bean -> {
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
        PageInfo<CollectionEntity> pageEntity = collectionRepository.getPageListByCreatorIdAndCommodityType(current, pageSize, creatorId, NftConstants.商品类型_盲盒);
        List<MysteryBoxRespDTO> resultList = new ArrayList<>();
        pageEntity.getList().forEach(bean -> {
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
