package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.DictTypeEntity;
import org.sziit.infrastructure.dao.mapper.DictTypeMapper;
import org.sziit.infrastructure.repository.DictTypeRepository;

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
}




