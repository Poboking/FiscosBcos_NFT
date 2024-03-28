package org.knight.app.biz.artwork.collection;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.knight.app.biz.artwork.dto.holdcollection.MemberHoldCollectionRespDTO;
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
 * @date: 2024/3/8 20:48
 */
@Service
@AllArgsConstructor
public class MemberCollectionService {
    @Autowired
    private MemberHoldCollectionRepositoryImpl memberHoldCollectionRepository;
    @Autowired
    private CollectionRepositoryImpl collectionRepository;

    public PageResult<MemberHoldCollectionEntity> getPageList(long current, long pageSize, String memberId) {
        IPage<MemberHoldCollectionEntity> pageResult = memberHoldCollectionRepository.getPageListByMemberId(current, pageSize, memberId);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<MemberHoldCollectionEntity> getPageList(long current, long pageSize, String name, String memberId) {
        IPage<MemberHoldCollectionEntity> pageResult = memberHoldCollectionRepository.getPageListByMemberIdAndName(current, pageSize, name, memberId);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<MyHoldCollectionRespDTO> getHoldCollectionPageList(long current, long pageSize, String memberId) {
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageListByCommodityType(current, pageSize, NftConstants.商品类型_藏品);
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

    // TODO: 2024/3/28 待实现
    public PageResult<MemberHoldCollectionRespDTO> findMemberHoldCollectionByPage(Long current, Long pageSize, String memberMobile, String collectionName, String state, String gainWay) {
        return null;
    }
}