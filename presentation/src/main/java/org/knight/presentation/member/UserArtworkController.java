package org.knight.presentation.member;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.artwork.collection.MemberCollectionService;
import org.knight.app.biz.artwork.collection.MemberResaleCollectionService;
import org.knight.app.biz.artwork.dto.holdcollection.*;
import org.knight.app.biz.artwork.mysteryBox.MemberMysteryBoxService;
import org.knight.app.biz.convert.artwork.MyHoldCollectionDetailConvert;
import org.knight.app.biz.convert.artwork.MyResaleCollectionDetailConvert;
import org.knight.infrastructure.common.PageResult;
import org.knight.presentation.utils.StpUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 20:31
 */
//@CheckUserLogin
@Log4j2
@RestController
@RequestMapping("/myArtwork/")
@Tag(name = "UserArtworkController", description = "USER_ARTWORK_CONTROLLER")
public class UserArtworkController {
    private final MemberCollectionService memberCollectionService;
    private final MemberMysteryBoxService memberMysteryBoxService;
    private final MemberResaleCollectionService memberResaleCollectionService;

    @Autowired
    public UserArtworkController(MemberCollectionService memberCollectionService, MemberMysteryBoxService memberMysteryBoxService, MemberResaleCollectionService memberResaleCollectionService) {
        this.memberCollectionService = memberCollectionService;
        this.memberMysteryBoxService = memberMysteryBoxService;
        this.memberResaleCollectionService = memberResaleCollectionService;
    }


    @GetMapping("findMyHoldCollectionByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MyHoldCollectionRespDTO> findMyHoldCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize) {
        String memberId = StpUserUtil.getLoginIdAsString();
        return memberCollectionService.getMyHoldCollectionPageList(current, pageSize, memberId);
    }


    @GetMapping("findMyHoldMysteryBoxByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MyHoldCollectionRespDTO> findMyHoldMysteryBoxByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize) {
        String memberId = StpUserUtil.getLoginIdAsString();
        return memberMysteryBoxService.getHoldMysteryBoxPageList(current, pageSize, memberId);
    }


    @GetMapping("findMySoldCollectionByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MySaleCollectionRespDTO> findMySoldCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize) {
        String memberId = StpUserUtil.getLoginIdAsString();
        return memberResaleCollectionService.getMySoldCollectionPageList(current, pageSize, memberId);
    }

    @GetMapping("findMyResaleCollectionByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MyResaleCollectionRespDTO> findMyResaleCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize) {
        String memberId = StpUserUtil.getLoginIdAsString();
        return memberResaleCollectionService.getMyResaleCollectionPageList(current, pageSize, memberId);
    }


    // TODO: 2024/4/6 这里需处理commodityType为2时, subCommodity属性的填充.
    // TODO: 2024/4/6 此外还需要重构下MyholdCollectionbConvert 将memberholdcollectionconvert的部分方法移动到MyholdCollectionbConvert中
    @GetMapping("findMyHoldCollectionDetail")
    @ValidationStatusCode(code = "400")
    public MyHoldCollectionDetailRespDTO findMyHoldCollectionDetail(
            @RequestParam(name = "id")String collectionId){
        String memberId = StpUserUtil.getLoginIdAsString();
        return memberCollectionService.getMyHoldCollectionDetail(collectionId, memberId);
    }

    @GetMapping("findMyResaleCollectionDetail")
    @ValidationStatusCode(code = "400")
    public MyResaleCollectionDetailRespDTO findMyResaleCollectionDetail(
            @RequestParam(name = "id")String collectionId){
        String memberId = StpUserUtil.getLoginIdAsString();
        return memberResaleCollectionService.getMyResaleCollectionDetail(collectionId, memberId);
    }

}
