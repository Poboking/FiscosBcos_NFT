package org.sziit.presentation.member;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sziit.app.biz.artwork.collection.CollectionService;
import org.sziit.app.biz.artwork.collection.MemberResaleCollectionService;
import org.sziit.app.biz.artwork.dto.collection.CollectionDetailRespDto;
import org.sziit.app.biz.artwork.dto.collection.CollectionQueryReqDto;
import org.sziit.app.biz.artwork.dto.creator.CreatorRespDto;
import org.sziit.app.biz.artwork.mysteryBox.MysteryBoxCommodityService;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.dao.domain.MysteryBoxCommodityEntity;

import java.io.Serializable;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 10:58
 */
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/collection/")
@Tag(name = "CollectionController", description = "COLLECTION_CONTROLLER")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private MemberResaleCollectionService memberResaleCollectionService;
    @Autowired
    private MysteryBoxCommodityService mysteryBoxCommodityService;


    @GetMapping("findLatestCollectionByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<CollectionEntity> findLatestCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "creatorId", required = false) String creatorId) {
        if (creatorId == null) {
            return collectionService.getPageList(current, pageSize);
        }
        return collectionService.getPageListByCreatorId(current, pageSize, creatorId);
    }

    /**
     * Get 请求没法处理@RequestBody (╬▔皿▔)╯
     */
    @GetMapping("findResaleCollectionByPage")
    @ValidationStatusCode(code = "400")
    public Serializable findResaleCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "creatorId", required = false) String creatorId,
            @RequestParam(name = "collectionType", required = false, defaultValue = "1") String collectionType,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "collectionId", required = false) String collectionId) {
        CollectionQueryReqDto reqDto = CollectionQueryReqDto.builder()
                .current(current)
                .pageSize(pageSize)
                .creatorId(creatorId)
                .collectionType(collectionType)
                .direction(direction)
                .collectionId(collectionId)
                .build();
        if (reqDto.getCollectionType().equals("1")) {
            return memberResaleCollectionService.getPageListByCollectionQueryParam(reqDto);
        } else {
            return mysteryBoxCommodityService.getPageListByCollectionQueryParam(reqDto);
        }
    }

    @GetMapping("findLatestCollectionDetailById")
    @ValidationStatusCode(code = "400")
    public CollectionDetailRespDto findLatestCollectionDetailById(
            @RequestParam(name = "id", required = true) String collectionId) {
        return collectionService.getCollectionDetail(collectionId);
    }

    @GetMapping("findCreatorById")
    @ValidationStatusCode(code = "400")
    public CreatorRespDto findCreatorById(
            @RequestParam(name = "id", required = true) String collectionId) {
        return collectionService.getCreatorById(collectionId);
    }


    @GetMapping("findLatestMysteryBoxByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MysteryBoxCommodityEntity> findLatestMysteryBoxByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "creatorId", required = false) String creatorId) {
        if (creatorId == null) {
            return mysteryBoxCommodityService.getPageList(current, pageSize);
        }
        return mysteryBoxCommodityService.getPageListByCreatorId(current, pageSize, creatorId);
    }
}
