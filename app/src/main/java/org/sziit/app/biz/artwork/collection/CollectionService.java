package org.sziit.app.biz.artwork.collection;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.artwork.dto.collection.*;
import org.sziit.app.biz.artwork.dto.creator.CreatorRespDTO;
import org.sziit.app.biz.convert.artwork.CollectionConvert;
import org.sziit.app.biz.convert.artwork.CreatorConvert;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.sziit.infrastructure.repository.impl.CreatorRepositoryImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
}
