package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.dao.domain.NoticeEntity;
import org.knight.infrastructure.dao.mapper.NoticeMapper;
import org.knight.infrastructure.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @return PageInfo<NoticeEntity> 通知公告的分页列表
     */
    @Override
    public PageInfo<NoticeEntity> getPageList(long current, long pageSize) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<NoticeEntity> list = noticeMapper.selectList(null);
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取通知公告的分页列表
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param title    标题
     * @return PageInfo<NoticeEntity> 通知公告的分页列表
     */
    @Override
    public PageInfo<NoticeEntity> getPageListByTitle(long current, long pageSize, String title) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<NoticeEntity> list = noticeMapper.selectList(new QueryWrapper<NoticeEntity>()
                    .eq(!CharSequenceUtil.isBlank(title), "title", title));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取通知公告的分页列表
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param type     类型
     * @return PageInfo<NoticeEntity> 通知公告的分页列表
     */
    @Override
    public PageInfo<NoticeEntity> getPageListByType(long current, long pageSize, String type) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<NoticeEntity> list = noticeMapper.selectList(new QueryWrapper<NoticeEntity>()
                    .eq(!CharSequenceUtil.isBlank(type), "type", type));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
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
                .eq(!CharSequenceUtil.isBlank(noticeId), "id", noticeId));
    }
}




