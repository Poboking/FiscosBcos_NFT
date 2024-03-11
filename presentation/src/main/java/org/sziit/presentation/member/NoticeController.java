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
import org.sziit.app.biz.notice.NoticeService;
import org.sziit.app.biz.notice.dto.NoticeAbstractRespDto;
import org.sziit.app.biz.notice.dto.NoticeDetailRespDto;
import org.sziit.infrastructure.common.PageResult;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 15:11
 */
@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/notice/")
@Tag(name = "NoticeController", description = "NOTICE_CONTROLLER")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("findNoticeAbstractByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<NoticeAbstractRespDto> findNoticeAbstractByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "type", defaultValue = "1") String type) {
        return noticeService.getNoticeAbstractPageListByType(current, pageSize, type);
    }

    @GetMapping("getNoticeDetail")
    @ValidationStatusCode(code = "400")
    public NoticeDetailRespDto getNoticeDetail(
            @RequestParam(name = "noticeId", required = true) String noticeId) {
        return noticeService.getNoticeDetail(noticeId);
    }
}
