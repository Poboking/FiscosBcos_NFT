package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.NoticeEntity;
import org.sziit.infrastructure.dao.mapper.NoticeMapper;
import org.sziit.infrastructure.repository.NoticeRepository;

import java.util.Optional;

/**
 * @author poboking
 * @description 针对表【notice】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class NoticeRepositoryImpl extends ServiceImpl<NoticeMapper, NoticeEntity>
        implements NoticeRepository {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 获取通知公告的分页列表
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<NoticeEntity> 通知公告的分页列表
     */
    @Override
    public IPage<NoticeEntity> getPageList(long current, long pageSize) {
        return noticeMapper.selectPage(new Page<>(current, pageSize), null);
    }

    /**
     * 获取通知公告的分页列表
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param title    标题
     * @return IPage<NoticeEntity> 通知公告的分页列表
     */
    @Override
    public IPage<NoticeEntity> getPageListByTitle(long current, long pageSize, String title) {
        return noticeMapper.selectPage(new Page<>(current, pageSize), new QueryWrapper<NoticeEntity>()
                .eq(Optional.ofNullable(title).isPresent(), "title", title));
    }

    /**
     * 获取通知公告的分页列表
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param type     类型
     * @return IPage<NoticeEntity> 通知公告的分页列表
     */
    @Override
    public IPage<NoticeEntity> getPageListByType(long current, long pageSize, String type) {
        return noticeMapper.selectPage(new Page<>(current, pageSize), new QueryWrapper<NoticeEntity>()
                .eq(Optional.ofNullable(type).isPresent(), "type", type));
    }

    /**
     * 获取通知公告的详情
     *
     * @param noticeId 通知公告的唯一标识ID
     * @return NoticeEntity 通知公告的详情
     */
    @Override
    public NoticeEntity getNoticeDetail(String noticeId) {
        return noticeMapper.selectOne(new QueryWrapper<NoticeEntity>()
                .eq(Optional.ofNullable(noticeId).isPresent(), "id", noticeId));
    }
}




