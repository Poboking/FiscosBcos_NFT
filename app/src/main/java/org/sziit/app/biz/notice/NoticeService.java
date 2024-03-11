package org.sziit.app.biz.notice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.convert.notice.NoticeConvert;
import org.sziit.app.biz.notice.dto.NoticeAbstractRespDto;
import org.sziit.app.biz.notice.dto.NoticeDetailRespDto;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.NoticeEntity;
import org.sziit.infrastructure.repository.impl.NoticeRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 15:47
 */
@Service
@AllArgsConstructor
public class NoticeService {
    @Autowired
    private NoticeRepositoryImpl noticeRepository;

    public PageResult<NoticeAbstractRespDto> getNoticeAbstractPageList(long current, long pageSize) {
        IPage<NoticeEntity> page = noticeRepository.getPageList(current, pageSize);
        List<NoticeAbstractRespDto> pageResultList = new ArrayList<>();
        page.getRecords().forEach(noticeEntity -> pageResultList.add(NoticeConvert.INSTANCE.convertToAbstract(noticeEntity)));
        return PageResult.convertFor(page, pageSize, pageResultList);
    }

    public PageResult<NoticeAbstractRespDto> getNoticeAbstractPageListByType(long current, long pageSize, String type) {
        IPage<NoticeEntity> page = noticeRepository.getPageListByType(current, pageSize, type);
        List<NoticeAbstractRespDto> pageResultList = new ArrayList<>();
        page.getRecords().forEach(noticeEntity -> pageResultList.add(NoticeConvert.INSTANCE.convertToAbstract(noticeEntity)));
        return PageResult.convertFor(page, pageSize, pageResultList);
    }

    public NoticeDetailRespDto getNoticeDetail(String noticeId) {
        return NoticeConvert.INSTANCE.convertToDetail(noticeRepository.getNoticeDetail(noticeId));
    }
}
