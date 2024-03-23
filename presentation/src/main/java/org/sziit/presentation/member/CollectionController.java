package org.sziit.presentation.member;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.sziit.app.biz.artwork.dto.collection.*;
import org.sziit.app.biz.artwork.dto.creator.CreatorRespDTO;
import org.sziit.app.biz.artwork.dto.mysteryBox.MysteryBoxRespDTO;
import org.sziit.app.biz.artwork.mysteryBox.MysteryBoxService;
import org.sziit.infrastructure.common.PageResult;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 10:58
 */
//@CheckUserLogin
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
    private MysteryBoxService mysteryBoxService;


    @GetMapping("findLatestCollectionByPage")
    @ValidationStatusCode(code = "400")
    @Parameter(name = "current", description = "当前页", required = true)
    @Parameter(name = "pageSize", description = "每页大小", required = true)
    public PageResult<CollectionIntroRespDTO> findLatestCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "creatorId", required = false) String creatorId) {
        if (creatorId == null) {
            return collectionService.getIntroPageList(current, pageSize);
        }
        return collectionService.getIntroPageListByCreatorId(current, pageSize, creatorId);
    }

    /**
     * Get 请求没法处理@RequestBody (╬▔皿▔)╯
     */
    @GetMapping("findResaleCollectionByPage")
    @ValidationStatusCode(code = "400")
    @Parameter(name = "current", description = "当前页", required = true)
    @Parameter(name = "pageSize", description = "每页大小", required = true)
    public PageResult<CollectionResaleRespDTO> findResaleCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "creatorId", required = false) String creatorId,
            @RequestParam(name = "collectionType", required = false, defaultValue = "1") String collectionType,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "collectionId", required = false) String collectionId) {
        CollectionQueryReqDTO reqDto = CollectionQueryReqDTO.builder()
                .current(current)
                .pageSize(pageSize)
                .creatorId(creatorId)
                .commodityType(collectionType)
                .direction(direction)
                .collectionId(collectionId)
                .build();
        return memberResaleCollectionService.getPageListByCollectionQueryParam(reqDto);
    }


    @GetMapping("findLatestCollectionDetailById")
    @ValidationStatusCode(code = "400")
    public CollectionDetailRespDTO findLatestCollectionDetailById(
            @RequestParam(name = "id", required = true) String collectionId) {
        return collectionService.getCollectionDetail(collectionId);
    }

    @GetMapping("findCreatorById")
    @ValidationStatusCode(code = "400")
    public CreatorRespDTO findCreatorById(
            @RequestParam(name = "id", required = true) String collectionId) {
        return collectionService.getCreatorById(collectionId);
    }


    @GetMapping("findLatestMysteryBoxByPage")
    @ValidationStatusCode(code = "400")
    @Parameter(name = "current", description = "当前页", required = true)
    @Parameter(name = "pageSize", description = "每页大小", required = true)
    public PageResult<MysteryBoxRespDTO> findLatestMysteryBoxByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "creatorId", required = false) String creatorId) {
        if (creatorId == null) {
            return mysteryBoxService.getPageList(current, pageSize);
        }
        return mysteryBoxService.getPageListByCreatorId(current, pageSize, creatorId);
    }

    /**
     * 首页 - 发售日历
     */
    @GetMapping("findForSaleCollection")
    @ValidationStatusCode(code = "500")
    public List<GroupByDateCollectionRespDTO> findForSaleCollection() {
        return collectionService.findForSaleCollection();
    }
}
