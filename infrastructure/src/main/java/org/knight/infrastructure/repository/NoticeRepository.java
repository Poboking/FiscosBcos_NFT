package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.knight.infrastructure.dao.domain.NoticeEntity;

/**
 * @author poboking
 * @description 针对表【notice】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface NoticeRepository extends IService<NoticeEntity> {
    /**
     * 获取通知公告的分页列表
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<NoticeEntity> 通知公告的分页列表
     */
    IPage<NoticeEntity> getPageList(long current, long pageSize);

    /**
     * 获取通知公告的分页列表
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param title    标题
     * @return IPage<NoticeEntity> 通知公告的分页列表
     */
    IPage<NoticeEntity> getPageListByTitle(long current, long pageSize, String title);

    /**
     * 获取通知公告的分页列表
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param type     类型
     * @return IPage<NoticeEntity> 通知公告的分页列表
     */
    IPage<NoticeEntity> getPageListByType(long current, long pageSize, String type);

    /**
     * 获取通知公告的详情
     *
     * @param noticeId 通知公告的唯一标识ID
     * @return NoticeEntity 通知公告的详情
     */
    NoticeEntity getNoticeDetail(String noticeId);
}
