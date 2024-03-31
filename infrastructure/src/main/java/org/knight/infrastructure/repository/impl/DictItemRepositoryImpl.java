package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.DictItemEntity;
import org.knight.infrastructure.dao.mapper.DictItemMapper;
import org.knight.infrastructure.repository.DictItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author poboking
 * @description 针对表【dict_item】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class DictItemRepositoryImpl extends ServiceImpl<DictItemMapper, DictItemEntity>
        implements DictItemRepository {

    @Autowired
    private DictItemMapper dictItemMapper;

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<DictItemEntity> 分页数据
     */
    @Override
    public IPage<DictItemEntity> getPageList(long current, long pageSize) {
        return dictItemMapper.selectPage(new Page<>(current, pageSize), null);
    }

    /**
     * 分页查询
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param dictTypeCode 字典类型编码
     * @return IPage<DictItemEntity> 分页数据
     */
    @Override
    public IPage<DictItemEntity> getPageListByDictTypeCode(long current, long pageSize, String dictTypeCode) {
        return dictItemMapper.selectPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(current, pageSize)
                , new QueryWrapper<DictItemEntity>()
                        .eq(Optional.ofNullable(dictTypeCode).isPresent(), "dict_type_code", dictTypeCode));
    }

    /**
     * 根据字典类型编码获取字典项列表
     *
     * @param dictTypeId 字典类型编码
     * @return List<DictItemEntity> 字典项列表
     */
    @Override
    public List<DictItemEntity> getListByDictTypeId(String dictTypeId) {
        return dictItemMapper.selectList(new QueryWrapper<DictItemEntity>()
                .eq(Optional.ofNullable(dictTypeId).isPresent(), "dict_type_id", dictTypeId));
    }
}




