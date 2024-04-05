package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import org.knight.infrastructure.dao.domain.IssuedCollectionEntity;

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
}
