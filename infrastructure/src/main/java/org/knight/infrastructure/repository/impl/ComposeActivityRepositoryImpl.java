package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.ComposeActivityEntity;
import org.knight.infrastructure.dao.mapper.ComposeActivityMapper;
import org.knight.infrastructure.repository.ComposeActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author poboking
 * @description 针对表【compose_activity】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class ComposeActivityRepositoryImpl extends ServiceImpl<ComposeActivityMapper, ComposeActivityEntity>
        implements ComposeActivityRepository {
    @Autowired
    private ComposeActivityMapper composeActivityMapper;

    /**
     * 获取活动信息 - 全部
     *
     * @return 活动信息
     */
    @Override
    public List<ComposeActivityEntity> getComposeActivityList() {
        return composeActivityMapper.selectList(new QueryWrapper<ComposeActivityEntity>()
                .orderByDesc("create_time"));
    }

    /**
     * 获取活动信息 - 根据ID
     *
     * @param id 活动ID
     * @return 活动信息
     */
    @Override
    public ComposeActivityEntity getComposeActivity(String id) {
        return composeActivityMapper.selectById(id);
    }

    /**
     * 获取活动信息 - 根据标题
     *
     * @param title 活动标题
     * @return 活动信息
     */
    @Override
    public ComposeActivityEntity getComposeActivityByTitle(String title) {
        return composeActivityMapper.selectOne(new QueryWrapper<ComposeActivityEntity>()
                .eq(!CharSequenceUtil.isBlank(title), "title", title));
    }
}




