package org.knight.app.biz.notice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.knight.app.biz.convert.notice.notice.NoticeConvert;
import org.knight.app.biz.notice.dto.notice.NoticeAbstractRespDTO;
import org.knight.app.biz.notice.dto.notice.NoticeDetailRespDTO;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.NoticeEntity;
import org.knight.infrastructure.repository.impl.NoticeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public PageResult<NoticeAbstractRespDTO> getNoticeAbstractPageList(long current, long pageSize) {
        IPage<NoticeEntity> page = noticeRepository.getPageList(current, pageSize);
        List<NoticeAbstractRespDTO> pageResultList = new ArrayList<>();
        page.getRecords().forEach(noticeEntity -> pageResultList.add(NoticeConvert.INSTANCE.convertToAbstract(noticeEntity)));
        return PageResult.convertFor(page, pageSize, pageResultList);
    }

    public PageResult<NoticeAbstractRespDTO> getNoticeAbstractPageListByType(long current, long pageSize, String type) {
        IPage<NoticeEntity> page = noticeRepository.getPageListByType(current, pageSize, type);
        List<NoticeAbstractRespDTO> pageResultList = new ArrayList<>();
        page.getRecords().forEach(noticeEntity -> pageResultList.add(NoticeConvert.INSTANCE.convertToAbstract(noticeEntity)));
        return PageResult.convertFor(page, pageSize, pageResultList);
    }

    public NoticeDetailRespDTO getNoticeDetail(String noticeId) {
        return NoticeConvert.INSTANCE.convertToDetail(noticeRepository.getNoticeDetail(noticeId));
    }
}
