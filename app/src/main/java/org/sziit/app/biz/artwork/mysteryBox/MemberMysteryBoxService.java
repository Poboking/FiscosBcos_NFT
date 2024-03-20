package org.sziit.app.biz.artwork.mysteryBox;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.artwork.dto.myholdcollection.MyHoldCollectionRespDTO;
import org.sziit.app.biz.convert.artwork.MyHoldCollectionConvert;
import org.sziit.infrastructure.common.NftConstants;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.dao.domain.MemberHoldCollectionEntity;
import org.sziit.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.sziit.infrastructure.repository.impl.MemberHoldCollectionRepositoryImpl;

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
