package org.knight.app.biz.artwork.mysteryBox;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.knight.app.biz.artwork.dto.holdcollection.MyHoldCollectionRespDTO;
import org.knight.app.biz.convert.artwork.MyHoldCollectionConvert;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.domain.MemberHoldCollectionEntity;
import org.knight.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberHoldCollectionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/11 15:29
 */
@Service
public class MemberMysteryBoxService {
    private final MemberHoldCollectionRepositoryImpl memberHoldCollectionRepository;
    private final CollectionRepositoryImpl collectionRepository;

    @Autowired
    public MemberMysteryBoxService(MemberHoldCollectionRepositoryImpl memberHoldCollectionRepository, CollectionRepositoryImpl collectionRepository) {
        this.memberHoldCollectionRepository = memberHoldCollectionRepository;
        this.collectionRepository = collectionRepository;
    }

    public PageResult<MyHoldCollectionRespDTO> getHoldMysteryBoxPageList(long current, long pageSize, String memberId) {
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageListByCommodityType(current, pageSize, NftConstants.商品类型_盲盒);
        List<String> collectionIds = new ArrayList<>();
        pageEntity.getRecords().forEach(c -> collectionIds.add(c.getId()));
        IPage<MemberHoldCollectionEntity> pageHoldEntity = memberHoldCollectionRepository.getPageListByIdsAndMemberId(current, pageSize, collectionIds, memberId);
        List<MyHoldCollectionRespDTO> respDTOs = new ArrayList<>();
        pageHoldEntity.getRecords().forEach(e -> {
            pageEntity.getRecords().forEach(c -> {
                if (e.getCollectionId().equals(c.getId())) {
                    e.setName(Optional.ofNullable(c.getName()).orElse("DataError: Unknown Collection Name"));
                    e.setCover(Optional.ofNullable(c.getCover()).orElse("DataError: Unknown Collection Cover"));
                }
            });
            if (CharSequenceUtil.isBlank(e.getName())) {
                e.setName("DataError: Unknown Collection Name");
            }
            if (CharSequenceUtil.isBlank(e.getCover())) {
                e.setCover("DataError: Unknown Collection Cover");
            }
            MyHoldCollectionRespDTO respDTO = MyHoldCollectionConvert.convertToRespDTO(e);
            respDTO.setId(e.getId());
            respDTO.setHoldTime(DateUtil.format(e.getHoldTime(), NftConstants.DATE_FORMAT));
            respDTOs.add(respDTO);
        });
        return PageResult.convertFor(pageHoldEntity, pageSize, respDTOs);
    }

}
