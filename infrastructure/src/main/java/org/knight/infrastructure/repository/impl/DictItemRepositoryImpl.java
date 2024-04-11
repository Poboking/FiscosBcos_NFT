package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.dao.domain.DictItemEntity;
import org.knight.infrastructure.dao.mapper.DictItemMapper;
import org.knight.infrastructure.repository.DictItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @return PageInfo<DictItemEntity> 分页数据
     */
    @Override
    public PageInfo<DictItemEntity> getPageList(long current, long pageSize) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<DictItemEntity> list = dictItemMapper.selectList(null);
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param dictTypeCode 字典类型编码
     * @return PageInfo<DictItemEntity> 分页数据
     */
    @Override
    public PageInfo<DictItemEntity> getPageListByDictTypeCode(long current, long pageSize, String dictTypeCode) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<DictItemEntity> list = dictItemMapper.selectList(new QueryWrapper<DictItemEntity>()
                    .eq(!CharSequenceUtil.isBlank(dictTypeCode), "dict_type_code", dictTypeCode));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
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
                .eq(!CharSequenceUtil.isBlank(dictTypeId), "dict_type_id", dictTypeId));
    }
}




