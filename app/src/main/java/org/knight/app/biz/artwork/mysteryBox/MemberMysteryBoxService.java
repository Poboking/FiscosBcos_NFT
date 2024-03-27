package org.knight.app.biz.artwork.mysteryBox;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.knight.app.biz.artwork.dto.holdcollection.MyHoldCollectionRespDTO;
import org.knight.app.biz.convert.artwork.MyHoldCollectionConvert;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.domain.MemberHoldCollectionEntity;
import org.knight.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberHoldCollectionRepositoryImpl;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/11 15:29
 */
@Service
@AllArgsConstructor
public class MemberMysteryBoxService {
    @Autowired
    private MemberHoldCollectionRepositoryImpl memberHoldCollectionRepository;
    @Autowired
    private CollectionRepositoryImpl collectionRepository;

    public PageResult<MyHoldCollectionRespDTO> getHoldMysteryBoxPageList(long current, long pageSize, String memberId) {
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageListByCommodityType(current, pageSize, NftConstants.商品类型_盲盒);
        IPage<MemberHoldCollectionEntity> pageHoldEntity = memberHoldCollectionRepository.getPageListByMemberId(current, pageSize, memberId);
        List<MemberHoldCollectionEntity> records = pageHoldEntity.getRecords();
        records.forEach(e -> {
            pageEntity.getRecords().forEach(c -> {
                if (e.getCollectionId().equals(c.getId())) {
                    e.setName(c.getName());
                    e.setCover(c.getCover());
                }
            });
        });
        return PageResult.convertFor(pageHoldEntity, pageSize, MyHoldCollectionConvert.convertToRespDTO(records));
    }

}
