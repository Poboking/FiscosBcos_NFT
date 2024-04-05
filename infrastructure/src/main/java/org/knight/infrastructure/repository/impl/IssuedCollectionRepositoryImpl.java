package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.IssuedCollectionEntity;
import org.knight.infrastructure.dao.mapper.IssuedCollectionMapper;
import org.knight.infrastructure.repository.IssuedCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        if (Objects.isNull(entity) || CharSequenceUtil.isBlank(entity.getId())){
            return null;
        }
        return entity.getId();
    }
}




