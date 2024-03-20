package org.sziit.presentation.member;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sziit.app.biz.artwork.collection.MemberCollectionService;
import org.sziit.app.biz.artwork.collection.MemberResaleCollectionService;
import org.sziit.app.biz.artwork.dto.myholdcollection.MyHoldCollectionRespDTO;
import org.sziit.app.biz.artwork.mysteryBox.MemberMysteryBoxService;
import org.sziit.infrastructure.common.PageResult;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 20:31
 */
//@CheckUserLogin
@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/myArtwork/")
@Tag(name = "UserArtworkController", description = "USER_ARTWORK_CONTROLLER")
public class UserArtworkController {
    @Autowired
    private MemberCollectionService memberCollectionService;
    @Autowired
    private MemberMysteryBoxService memberMysteryBoxService;
    @Autowired
    private MemberResaleCollectionService memberResaleCollectionService;


    @GetMapping("findMyHoldCollectionByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MyHoldCollectionRespDTO> findMyHoldCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "memberId") String memberId) {
        return memberCollectionService.getHoldCollectionPageList(current, pageSize, memberId);
    }


    @GetMapping("findMyHoldMysteryBoxByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MyHoldCollectionRespDTO> findMyHoldMysteryBoxByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            String memberId) {
        return memberMysteryBoxService.getHoldMysteryBoxPageList(current, pageSize, memberId);
    }


    @GetMapping("findMySoldCollectionByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MyHoldCollectionRespDTO> findMySoldCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            String memberId) {
        return memberResaleCollectionService.getMySoldCollectionPageList(current, pageSize, memberId);
    }


}
