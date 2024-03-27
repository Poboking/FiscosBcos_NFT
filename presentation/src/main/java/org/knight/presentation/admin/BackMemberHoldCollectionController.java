package org.knight.presentation.admin;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.knight.app.biz.artwork.dto.holdcollection.MemberHoldCollectionRespDTO;
import org.knight.app.biz.log.dto.collectionlog.IssuedCollectionActionLogRespDTO;
import org.knight.infrastructure.common.PageResult;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/25 11:08
 */
@Log4j2
@RestController
@RequestMapping("/back/memberHoldCollection/")
public class BackMemberHoldCollectionController {

    @GetMapping("findMemberHoldCollectionByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MemberHoldCollectionRespDTO> findMemberHoldCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") Long current,
            @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize,
            @RequestParam(name = "memberMobile", required = false) String memberMobile,
            @RequestParam(name = "collectionName", required = false) String collectionName,
            @RequestParam(name = "state", defaultValue = "1") String state,
            @RequestParam(name = "gainWay", required = false) String gainWay
    ) {
        return null;
    }

    @GetMapping("findIssuedCollectionActionLog")
    @ValidationStatusCode(code = "400")
    public List<IssuedCollectionActionLogRespDTO> findIssuedCollectionActionLog(
            @RequestParam(name = "issuedCollectionId") String issuedCollectionId
    ) {
        return null;
    }
}
