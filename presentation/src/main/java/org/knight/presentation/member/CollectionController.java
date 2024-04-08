package org.knight.presentation.member;

import cn.hutool.core.text.CharSequenceUtil;
import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.artwork.collection.CollectionService;
import org.knight.app.biz.artwork.collection.MemberResaleCollectionService;
import org.knight.app.biz.artwork.dto.collection.*;
import org.knight.app.biz.artwork.dto.creator.CreatorRespDTO;
import org.knight.app.biz.artwork.dto.mysteryBox.MysteryBoxRespDTO;
import org.knight.app.biz.artwork.mysteryBox.MysteryBoxService;
import org.knight.app.biz.log.IssuedCollectionActLogService;
import org.knight.app.biz.log.dto.collectionlog.IssuedCollectionActionLogRespDTO;
import org.knight.infrastructure.common.PageResult;
import org.knight.presentation.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 10:58
 */
//@CheckUserLogin
@Log4j2
@RestController
@RequestMapping("/collection/")
@Tag(name = "CollectionController", description = "COLLECTION_CONTROLLER")
public class CollectionController {
    private final CollectionService collectionService;
    private final MemberResaleCollectionService memberResaleCollectionService;
    private final MysteryBoxService mysteryBoxService;

    private final IssuedCollectionActLogService issuedCollectionActLogService;

    @Autowired
    public CollectionController(CollectionService collectionService, MemberResaleCollectionService memberResaleCollectionService, MysteryBoxService mysteryBoxService, IssuedCollectionActLogService issuedCollectionActLogService) {
        this.collectionService = collectionService;
        this.memberResaleCollectionService = memberResaleCollectionService;
        this.mysteryBoxService = mysteryBoxService;
        this.issuedCollectionActLogService = issuedCollectionActLogService;
    }


    @GetMapping("findLatestCollectionByPage")
    @ValidationStatusCode(code = "400")
    @Parameter(name = "current", description = "当前页", required = true)
    @Parameter(name = "pageSize", description = "每页大小", required = true)
    public PageResult<CollectionIntroRespDTO> findLatestCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "creatorId", required = false) String creatorId) {
        if (CharSequenceUtil.isBlank(creatorId)) {
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

    @GetMapping("findResaleCollectionDetail")
    @ValidationStatusCode(code = "400")
    public CollectionResaleDetailRespDTO findResaleCollectionDetail(
            @RequestParam(name = "id", required = true) String collectionId) {
        return memberResaleCollectionService.getCollectionDetail(collectionId);
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
            @RequestParam(name = "id", required = true) String createId) {
        if (CharSequenceUtil.isBlank(createId)){
            throw new BadRequestException("creatorId不能为空");
        }
        return collectionService.getCreatorById(createId);
    }


    @GetMapping("findLatestMysteryBoxByPage")
    @ValidationStatusCode(code = "400")
    @Parameter(name = "current", description = "当前页", required = true)
    @Parameter(name = "pageSize", description = "每页大小", required = true)
    public PageResult<MysteryBoxRespDTO> findLatestMysteryBoxByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "creatorId", required = false) String creatorId) {
        if (CharSequenceUtil.isBlank(creatorId)) {
            return mysteryBoxService.getPageList(current, pageSize);
        }
        return mysteryBoxService.getPageListByCreatorId(current, pageSize, creatorId);
    }

    @GetMapping("findIssuedCollectionActionLog")
    @ValidationStatusCode(code = "500")
    public List<IssuedCollectionActionLogRespDTO> findIssuedCollectionActionLog(
            @RequestParam(name = "issuedCollectionId", required = true) String issuedCollectionId) {
        return issuedCollectionActLogService.findIssuedCollectionActionLog(issuedCollectionId);
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
