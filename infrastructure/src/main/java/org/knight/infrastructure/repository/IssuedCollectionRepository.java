package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import org.knight.infrastructure.dao.domain.IssuedCollectionEntity;

import java.util.List;

/**
 * @author poboking
 * @description 针对表【issued_collection】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface IssuedCollectionRepository extends IService<IssuedCollectionEntity> {

    /**
     * 根据藏品id和序列号获取发行id
     *
     * @param collectionId           藏品id
     * @param collectionSerialNumber 藏品序列号
     * @return String
     */
    String getIssuedIdByCollectionIdAndSerialNumber(String collectionId, Integer collectionSerialNumber);

    /**
     * 检查是否存在指定序列号的发行藏品
     *
     * @param collectionId 藏品id
     * @param serialNumber 序列号
     * @return boolean
     */
    boolean isExistSerialNumber(String collectionId, Integer serialNumber);

    /**
     * 检查指定藏品ID的发行数量
     *
     * @param collectionId 藏品ID
     * @return int 实际发行数量
     */
    Long checkIssuedCollectionCount(String collectionId);

    /**
     * 获取实际存在的序列号 - 指定藏品
     *
     * @param collectionId 藏品ID
     * @return list 返回的序列号List
     */
    List<Integer> getRealitySerialNumber(String collectionId);
}
