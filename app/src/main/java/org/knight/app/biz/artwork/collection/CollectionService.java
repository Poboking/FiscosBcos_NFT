package org.knight.app.biz.artwork.collection;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.knight.app.biz.artwork.dto.collection.*;
import org.knight.app.biz.artwork.dto.creator.CreatorRespDTO;
import org.knight.app.biz.convert.artwork.CollectionConvert;
import org.knight.app.biz.convert.artwork.CreatorConvert;
import org.knight.app.biz.exception.BizException;
import org.knight.infrastructure.common.IdUtils;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.domain.CollectionStoryEntity;
import org.knight.infrastructure.dao.domain.CreatorEntity;
import org.knight.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.CollectionStoryRepositoryImpl;
import org.knight.infrastructure.repository.impl.CreatorRepositoryImpl;
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


    public PageResult<CollectionEntity> getPageList(long current, long pageSize) {
        IPage<CollectionEntity> pageResult = collectionRepository.getPageList(current, pageSize);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<CollectionEntity> getPageListByName(long current, long pageSize, String name) {
        IPage<CollectionEntity> pageResult = collectionRepository.getPageListByName(current, pageSize, name);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public CollectionDetailRespDTO getCollectionDetail(String collectionId) {
        return CollectionConvert.INSTANCE.convertToDetailRespDTO(collectionRepository.getCollectionDetail(collectionId));
    }


    public CreatorRespDTO getCreatorById(String creatorId) {
        return CreatorConvert.INSTANCE.convertToRespDTO(creatorRepository.getById(creatorId));
    }

    public PageResult<CollectionEntity> getPageListByCreatorId(long current, long pageSize, String creatorId) {
        return PageResult.convertFor(collectionRepository.getPageListByCreatorId(current, pageSize, creatorId), pageSize);
    }

    public PageResult<CollectionIntroRespDTO> getIntroPageList(long current, long pageSize) {
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageList(current, pageSize);
        List<CollectionIntroRespDTO> resultList = new ArrayList<>();
        pageEntity.getRecords().forEach(bean -> {
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
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageListByCreatorId(current, pageSize, creatorId);
        List<CollectionIntroRespDTO> resultList = new ArrayList<>();
        pageEntity.getRecords().forEach(bean -> {
            CreatorRespDTO creator = getCreatorById(creatorId);
            if (creator == null) {
                return;
            }
            bean.setCreatorName(creator.getName());
            bean.setCreatorAvatar(creator.getAvatar());
            resultList.add(CollectionConvert.INSTANCE.convertToIntroRespDTO(bean));
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
        IPage<CollectionEntity> entityIPage = collectionRepository.getPageListByNameAndCommodityType(current, pageSize, name, commodityType);
        List<CollectionRespDTO> resultList = new ArrayList<>();
        entityIPage.getRecords().forEach(collectionEntity -> {
            resultList.add(CollectionConvert.INSTANCE.convertToRespDTO(collectionEntity));
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
            throw new BizException("删除收藏品失败");
        }
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
}
