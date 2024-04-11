package org.knight.app.biz.artwork.collection;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.knight.app.biz.artwork.dto.collection.*;
import org.knight.app.biz.artwork.dto.creator.CreatorRespDTO;
import org.knight.app.biz.convert.artwork.CollectionConvert;
import org.knight.app.biz.convert.artwork.CreatorConvert;
import org.knight.app.biz.exception.BizException;
import org.knight.app.biz.exception.collection.CollectionDeleteFailedException;
import org.knight.infrastructure.common.IdUtils;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.domain.CollectionStoryEntity;
import org.knight.infrastructure.dao.domain.CreatorEntity;
import org.knight.infrastructure.dao.domain.IssuedCollectionEntity;
import org.knight.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.CollectionStoryRepositoryImpl;
import org.knight.infrastructure.repository.impl.CreatorRepositoryImpl;
import org.knight.infrastructure.repository.impl.IssuedCollectionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 19:53
 */
@Service
@AllArgsConstructor
@SuppressWarnings("all")
public class CollectionService {
    @Autowired
    private CollectionRepositoryImpl collectionRepository;
    @Autowired
    private CreatorRepositoryImpl creatorRepository;
    @Autowired
    private CollectionStoryRepositoryImpl collectionStoryRepository;
    @Autowired
    private final IssuedCollectionRepositoryImpl issuedCollectionRepository;

    public PageResult<CollectionEntity> getPageList(long current, long pageSize) {
        PageInfo<CollectionEntity> pageResult = collectionRepository.getPageList(current, pageSize);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<CollectionEntity> getPageListByName(long current, long pageSize, String name) {
        PageInfo<CollectionEntity> pageResult = collectionRepository.getPageListByName(current, pageSize, name);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public CollectionDetailRespDTO getCollectionDetail(String collectionId) {
        CollectionDetailRespDTO respDTO = CollectionConvert.INSTANCE.convertToDetailRespDTO(collectionRepository.getCollectionDetail(collectionId));
        CreatorEntity creator = creatorRepository.getById(respDTO.getCreatorId());
        respDTO.setCreatorName(creator.getName());
        respDTO.setCreatorAvatar(creator.getAvatar());
        return respDTO;
    }


    public CreatorRespDTO getCreatorById(String creatorId) {
        if (Objects.isNull(creatorId)){
            return null;
        }
        CreatorEntity creator = creatorRepository.getById(creatorId);
        if (Objects.isNull(creator)){
            return null;
        }
        // TODO: 2024/4/8 需要将响应体的time属性都改为string类型
        return CreatorConvert.INSTANCE.convertToRespDTO(creator);
    }

    public PageResult<CollectionEntity> getPageListByCreatorId(long current, long pageSize, String creatorId) {
        return PageResult.convertFor(collectionRepository.getPageListByCreatorId(current, pageSize, creatorId), pageSize);
    }

    public PageResult<CollectionIntroRespDTO> getIntroPageList(long current, long pageSize) {
        PageInfo<CollectionEntity> pageEntity = collectionRepository.getPageList(current, pageSize);
        List<CollectionIntroRespDTO> resultList = new ArrayList<>();
        pageEntity.getList().forEach(bean -> {
            CreatorRespDTO creator = getCreatorById(bean.getCreatorId());
            if (creator == null) {
                return;
            }
            bean.setCreatorName(creator.getName());
            bean.setCreatorAvatar(creator.getAvatar());
            resultList.add(CollectionConvert.INSTANCE.convertToIntroRespDTO(bean));
        });
        return PageResult.convertFor(pageEntity, pageSize, resultList);
    }

    public PageResult<CollectionIntroRespDTO> getIntroPageListByCreatorId(long current, long pageSize, String creatorId) {
        CreatorRespDTO creator = getCreatorById(creatorId);
        if (creator == null) {
            return new PageResult<>();
        }
        PageInfo<CollectionEntity> pageEntity = collectionRepository.getPageListByCreatorId(current, pageSize, creatorId);
        List<CollectionIntroRespDTO> resultList = new ArrayList<>();
        pageEntity.getList().forEach(bean -> {
            bean.setCreatorName(creator.getName());
            bean.setCreatorAvatar(creator.getAvatar());
            CollectionIntroRespDTO respDTO = CollectionConvert.INSTANCE.convertToIntroRespDTO(bean);
            respDTO.setSaleTime(DateUtil.format(bean.getSaleTime(), "yyyy-MM-dd HH:mm:ss"));
            resultList.add(respDTO);
        });
        return PageResult.convertFor(pageEntity, pageSize, resultList);
    }

    public List<CollectionEntity> getAscListByDateParam(CollectionDateQueryReqDTO param) {
        return collectionRepository.getAscListByDateParam(param.getSaleTimeStart(), param.getSaleTimeEnd());
    }

    public List<CollectionEntity> getDescListByDateParam(CollectionDateQueryReqDTO param) {
        return collectionRepository.getDescListByDateParam(param.getSaleTimeStart(), param.getSaleTimeEnd());
    }

    public List<GroupByDateCollectionRespDTO> findForSaleCollection() {
        CollectionDateQueryReqDTO param = new CollectionDateQueryReqDTO();
        param.setSaleTimeStart(LocalDateTime.now());
        param.setSaleTimeEnd(LocalDateTime.now().plusDays(10));
        List<CollectionEntity> result = getAscListByDateParam(param);
        Map<String, List<CollectionEntity>> dateMap = new LinkedHashMap<String, List<CollectionEntity>>();
        for (CollectionEntity collection : result) {
            String date = DateUtil.format(collection.getSaleTime(), "MM月dd日");
            if (dateMap.get(date) == null) {
                dateMap.put(date, new ArrayList<>());
            }
            dateMap.get(date).add(collection);
        }
        List<GroupByDateCollectionRespDTO> groupByDateDTOs = new ArrayList<>();
        for (Map.Entry<String, List<CollectionEntity>> entry : dateMap.entrySet()) {
            Map<String, List<CollectionEntity>> timeMap = new LinkedHashMap<String, List<CollectionEntity>>();
            for (CollectionEntity collection : entry.getValue()) {
                String time = DateUtil.format(collection.getSaleTime(), "HH:mm");
                if (timeMap.get(time) == null) {
                    timeMap.put(time, new ArrayList<>());
                }
                timeMap.get(time).add(collection);
            }
            GroupByDateCollectionRespDTO groupByDateDTO = new GroupByDateCollectionRespDTO();
            for (Map.Entry<String, List<CollectionEntity>> time : timeMap.entrySet()) {
                GroupByTimeCollectionRespDTO groupByTimeDTO = new GroupByTimeCollectionRespDTO();
                groupByTimeDTO.setTime(time.getKey());
                groupByTimeDTO.setCollections(CollectionConvert.INSTANCE.convertToForSaleRespDTO(time.getValue()));
                groupByDateDTO.getTimeCollections().add(groupByTimeDTO);
            }
            groupByDateDTO.setDate(entry.getKey());
            groupByDateDTOs.add(groupByDateDTO);
        }
        return groupByDateDTOs;
    }

    public CollectionStatisticDataRespDTO getCollectionStatisticData() {
        return CollectionStatisticDataRespDTO.builder()
                .collectionCount(collectionRepository.count(new QueryWrapper<CollectionEntity>()
                        .and(wrapper -> wrapper.isNull("deleted_flag").or().eq("deleted_flag", false))
                        .eq("commodity_type", "1")))
                .mysteryBoxCount(collectionRepository.count(new QueryWrapper<CollectionEntity>()
                        .and(wrapper -> wrapper.isNull("deleted_flag").or().eq("deleted_flag", false))
                        .eq("commodity_type", "2")))
                .build();
    }

    public PageResult<CollectionRespDTO> findCollectionByPage(long current, long pageSize, String commodityType, String name) {
        PageInfo<CollectionEntity> entityIPage = collectionRepository.getPageListByNameAndCommodityType(current, pageSize, name, commodityType);
        List<CollectionRespDTO> resultList = new ArrayList<CollectionRespDTO>();
        entityIPage.getList().forEach(collectionEntity -> {
            CollectionRespDTO respDTO = CollectionConvert.INSTANCE.convertToRespDTO(collectionEntity);
            if (Objects.isNull(respDTO.getCollectionStorys())){
                respDTO.setCollectionStorys(new ArrayList<>());
            }
            resultList.add(respDTO);
        });
        return PageResult.convertFor(entityIPage, pageSize, resultList);
    }

    public Boolean updateCollectionStory(CollectionUpdateStoryReqDTO reqDTO) {
        if (reqDTO.getCollectionId() == null || reqDTO.getPicLinks() == null || reqDTO.getPicLinks().isEmpty()) {
            return false;
        }
        Set<Map.Entry<Double, String>> set = reqDTO.getPicLinks().entrySet();
        set.forEach(entry -> {
            if (collectionStoryRepository.getOne(new QueryWrapper<CollectionStoryEntity>()
                    .eq("collection_id", reqDTO.getCollectionId())
                    .eq("order_no", entry.getKey())) != null) {
                collectionStoryRepository.saveOrUpdate(CollectionStoryEntity.builder()
                        .picLink(entry.getValue())
                        .build(), new QueryWrapper<CollectionStoryEntity>()
                        .eq("collection_id", reqDTO.getCollectionId())
                        .eq("order_no", entry.getKey()));
                set.remove(entry);
                return;
            }
            collectionStoryRepository.saveOrUpdate(CollectionStoryEntity.builder()
                    .id(IdUtils.snowFlakeId())
                    .collectionId(reqDTO.getCollectionId())
                    .orderNo(entry.getKey())
                    .picLink(entry.getValue())
                    .build());
            set.remove(entry);
        });
        return set.isEmpty();
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean delCollection(String id) {
        String now = LocalDateTime.now().format(NftConstants.DATE_FORMAT);
        CollectionEntity entity = collectionRepository.getById(id);
        entity.setDeletedFlag(true);
        entity.setDeletedTime(Timestamp.valueOf(now));
        if (Boolean.FALSE.equals(collectionRepository.saveOrUpdate(entity, new QueryWrapper<CollectionEntity>().eq("id", id)))) {
            throw new CollectionDeleteFailedException("删除收藏品失败");
        }
        List<IssuedCollectionEntity> issuedCollection = issuedCollectionRepository.list(new QueryWrapper<IssuedCollectionEntity>()
                .eq("collection_id",entity.getId()));
        issuedCollection.forEach(issuedCollectionEntity -> {
            issuedCollectionEntity.setDeletedFlag(true);
            issuedCollectionEntity.setDeletedTime(Timestamp.valueOf(now));
            if (Boolean.FALSE.equals(issuedCollectionRepository.saveOrUpdate(issuedCollectionEntity, new QueryWrapper<IssuedCollectionEntity>().eq("id", issuedCollectionEntity.getId())))) {
                throw new CollectionDeleteFailedException("删除发行收藏品失败");
            }
        });
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean addCollection(CollectionAddReqDTO reqDTO) {
        String now = LocalDateTime.now().format(NftConstants.DATE_FORMAT);
        CollectionEntity entity = CollectionAddReqDTO.buildEntity(reqDTO);
        CreatorEntity creator = creatorRepository.getById(reqDTO.getCreatorId());
        if (creator == null) {
            return false;
        }
        entity.setCommodityType(reqDTO.getCommodityType());
        entity.setSaleTime(Timestamp.valueOf(reqDTO.getSaleTime()));
        entity.setStock(reqDTO.getQuantity());
        entity.setCreatorAvatar(creator.getAvatar());
        entity.setCreatorName(creator.getName());
        entity.setId(IdUtils.snowFlakeId());
        entity.setCreateTime(Timestamp.valueOf(now));
        if (Boolean.FALSE.equals(collectionRepository.save(entity))){
            throw new BizException("添加收藏品失败");
        }
        return true;
    }

    public String findCollectionId(String name, String creatorId) {
        CollectionEntity entity = collectionRepository.getOne(new QueryWrapper<CollectionEntity>()
                .eq(Optional.ofNullable(name).isPresent(), "name", name)
                .eq(Optional.ofNullable(creatorId).isPresent(), "creator_id", creatorId));
        if (entity == null) {
            throw new BizException("未找到对应的收藏品");
        }
        return entity.getId();
    }

    public Boolean updateCollectionHash(String collectionId, String collectionHash) {
        return collectionRepository.update(new UpdateWrapper<CollectionEntity>()
                .eq("id", collectionId)
                .set("collection_hash",collectionHash));
    }
}
