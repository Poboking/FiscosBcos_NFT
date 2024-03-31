package org.knight.presentation.member;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.log.MemberBCLogService;
import org.knight.app.biz.log.dto.balance.MemberBCLogRespDTO;
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
 * @date: 2024/3/17 21:55
 */
//@CheckUserLogin
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/memberBalanceChangeLog/")
@Tag(name = "MemberBalanceChangeLogController", description = "MEMBER_BALANCE_CHANGE_LOG_CONTROLLER")
public class MemberBalanceChangeLogController {
    @Autowired
    private MemberBCLogService memberBCLogService;

    @GetMapping("findByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<MemberBCLogRespDTO> findByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "changeType", required = false) String changeType) {
        String memberId = StpUserUtil.getLoginId("-1");
        return memberBCLogService.getMemberBCLog(current, pageSize, changeType, memberId);
    }

}
