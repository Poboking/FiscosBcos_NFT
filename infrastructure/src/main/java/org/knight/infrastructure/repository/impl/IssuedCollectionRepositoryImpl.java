package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.IssuedCollectionEntity;
import org.knight.infrastructure.dao.mapper.IssuedCollectionMapper;
import org.knight.infrastructure.repository.IssuedCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author poboking
 * @description 针对表【issued_collection】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class IssuedCollectionRepositoryImpl extends ServiceImpl<IssuedCollectionMapper, IssuedCollectionEntity>
        implements IssuedCollectionRepository {

    private final IssuedCollectionMapper issuedCollectionMapper;

    @Autowired
    public IssuedCollectionRepositoryImpl(IssuedCollectionMapper issuedCollectionMapper) {
        this.issuedCollectionMapper = issuedCollectionMapper;
    }

    /**
     * 根据藏品id和序列号获取发行id
     *
     * @param collectionId           藏品id
     * @param collectionSerialNumber 藏品序列号
     * @return String
     */
    @Override
    public String getIssuedIdByCollectionIdAndSerialNumber(String collectionId, Integer collectionSerialNumber) {
        IssuedCollectionEntity entity = issuedCollectionMapper.selectOne(new QueryWrapper<IssuedCollectionEntity>()
                .eq("collection_id", collectionId)
                .eq("collection_serial_number", collectionSerialNumber));
        if (Objects.isNull(entity) || CharSequenceUtil.isBlank(entity.getId())) {
            return null;
        }
        return entity.getId();
    }

    /**
     * 检查是否存在指定序列号的发行藏品
     *
     * @param collectionId 藏品id
     * @param serialNumber 序列号
     * @return boolean
     */
    @Override
    public boolean isExistSerialNumber(String collectionId, Integer serialNumber) {
        return issuedCollectionMapper.exists(new QueryWrapper<IssuedCollectionEntity>()
                .eq("collection_id", collectionId)
                .eq("collection_serial_number", serialNumber));
    }

    /**
     * 检查指定藏品ID的发行数量
     *
     * @param collectionId 藏品ID
     * @return int 实际发行数量
     */
    @Override
    public Long checkIssuedCollectionCount(String collectionId) {
        return issuedCollectionMapper.selectCount(new QueryWrapper<IssuedCollectionEntity>()
                .eq("collection_id", collectionId));
    }

    /**
     * 获取实际存在的序列号 - 指定藏品
     *
     * @param collectionId 藏品ID
     * @return list 返回的序列号List
     */
    @Override
    public List<Integer> getRealitySerialNumber(String collectionId) {
        if (CharSequenceUtil.isBlank(collectionId)) {
            return null;
        }
        List<IssuedCollectionEntity> list = issuedCollectionMapper.selectList(new QueryWrapper<IssuedCollectionEntity>()
                .eq("collection_id", collectionId));
        if (Objects.isNull(list) || list.isEmpty()) {
            return null;
        }
        return list.stream().map(IssuedCollectionEntity::getCollectionSerialNumber)
                .collect(Collectors.toList());
    }
}




