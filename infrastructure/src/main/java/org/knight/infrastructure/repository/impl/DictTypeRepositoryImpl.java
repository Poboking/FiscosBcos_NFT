package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.DictTypeEntity;
import org.knight.infrastructure.dao.mapper.DictTypeMapper;
import org.knight.infrastructure.repository.DictTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @return IPage<DictTypeEntity> 分页数据
     */
    @Override
    public IPage<DictTypeEntity> getPageList(long current, long pageSize) {
        return dictTypeMapper.selectPage(new Page<>(current, pageSize), null);
    }

    /**
     * 根据字典类型编码获取字典类型ID
     *
     * @param dictTypeCode 字典类型编码
     * @return String 字典类型ID
     */
    @Override
    public String getIdByDictTypeCode(String dictTypeCode) {
        if (dictTypeCode == null || dictTypeCode.isEmpty()){
            return null;
        }
        return dictTypeMapper.selectList(null).stream()
                .filter(item -> item.getDictTypeCode().equals(dictTypeCode))
                .findFirst()
                .map(DictTypeEntity::getId)
                .orElse(null);
    }
}




