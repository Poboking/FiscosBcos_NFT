package org.sziit.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import org.sziit.infrastructure.dao.domain.ComposeActivityEntity;

import java.util.List;

/**
 * @author poboking
 * @description 针对表【compose_activity】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface ComposeActivityRepository extends IService<ComposeActivityEntity> {

    /**
     * 获取活动信息 - 全部
     *
     * @return 活动信息
     */
    List<ComposeActivityEntity> getComposeActivityList();

    /**
     * 获取活动信息 - 根据ID
     *
     * @param id 活动ID
     * @return 活动信息
     */
    ComposeActivityEntity getComposeActivity(String id);

    /**
     * 获取活动信息 - 根据标题
     *
     * @param title 活动标题
     * @return 活动信息
     */
    ComposeActivityEntity getComposeActivityByTitle(String title);
}
