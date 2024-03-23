package org.sziit.infrastructure.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.sziit.infrastructure.dao.domain.CollectionGiveRecordEntity;

/**
 * @author poboking
 * @description 针对表【collection_give_record】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface CollectionGiveRecordRepository extends IService<CollectionGiveRecordEntity> {

    /**
     * 根据赠送方ID获取赠送记录
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 赠送方ID
     * @return 赠送记录分页列表
     */
    IPage<CollectionGiveRecordEntity> getPageListByGiveFromId(long current, long pageSize, String memberId);


    /**
     * 根据接收方ID获取赠送记录
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 接收方ID
     * @return 赠送记录分页列表
     */
    IPage<CollectionGiveRecordEntity> getPageListByGiveToId(long current, long pageSize, String memberId);

    /**
     * 根据赠送方ID或接收方ID获取赠送记录
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param memberId  赠送方ID
     * @param memberId1 接收方ID
     * @return 赠送记录分页列表
     */
    IPage<CollectionGiveRecordEntity> getPageListByGiveToIdOrGiveFormId(long current, long pageSize, String memberId, String memberId1);
}
