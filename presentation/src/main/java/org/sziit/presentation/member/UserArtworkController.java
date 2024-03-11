package org.sziit.presentation.member;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sziit.app.biz.artwork.collection.MemberCollectionService;
import org.sziit.app.biz.artwork.collection.MemberResaleCollectionService;
import org.sziit.app.biz.artwork.mysteryBox.MemberMysteryBoxService;
import org.sziit.app.biz.vo.collection.MyHoldCollectionVo;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.MemberHoldCollectionEntity;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 20:31
 */
@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/myArtwork/")
@Tag(name = "UserArtworkController", description = "USER_ARTWORK_CONTROLLER")
public class UserArtworkController {
    @Resource
    private MemberCollectionService memberCollectionService;
    @Resource
    private MemberMysteryBoxService memberMysteryBoxService;
    @Resource
    private MemberResaleCollectionService memberResaleCollectionService;


    @GetMapping("findMyHoldCollectionByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MyHoldCollectionVo> findMyHoldCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "memberId") String memberId) {
        return memberCollectionService.getHoldCollectionPageList(current, pageSize, memberId);
    }


    @GetMapping("findMyHoldMysteryBoxByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MyHoldCollectionVo> findMyHoldMysteryBoxByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            String memberId) {
        return memberMysteryBoxService.getHoldMysteryBoxPageList(current, pageSize, memberId);
    }


    @GetMapping("findMySoldCollectionByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MyHoldCollectionVo> findMySoldCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            String memberId) {
        return memberResaleCollectionService.getMySoldCollectionPageList(current, pageSize, memberId);
    }


}
