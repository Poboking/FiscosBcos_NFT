package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.dao.domain.DictTypeEntity;
import org.knight.infrastructure.dao.mapper.DictTypeMapper;
import org.knight.infrastructure.repository.DictTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author poboking
 * @description 针对表【dict_type】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class DictTypeRepositoryImpl extends ServiceImpl<DictTypeMapper, DictTypeEntity>
        implements DictTypeRepository {
    @Autowired
    private DictTypeMapper dictTypeMapper;

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<DictTypeEntity> 分页数据
     */
    @Override
    public PageInfo<DictTypeEntity> getPageList(long current, long pageSize) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<DictTypeEntity> list = dictTypeMapper.selectList(null);
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 根据字典类型编码获取字典类型ID
     *
     * @param dictTypeCode 字典类型编码
     * @return String 字典类型ID
     */
    @Override
    public String getIdByDictTypeCode(String dictTypeCode) {
        if (dictTypeCode == null || dictTypeCode.isEmpty()) {
            return null;
        }
        return dictTypeMapper.selectList(null).stream()
                .filter(item -> item.getDictTypeCode().equals(dictTypeCode))
                .findFirst()
                .map(DictTypeEntity::getId)
                .orElse(null);
    }
}




