package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.knight.infrastructure.dao.domain.DictTypeEntity;

/**
 * @author poboking
 * @description 针对表【dict_type】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface DictTypeRepository extends IService<DictTypeEntity> {
    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<DictTypeEntity> 分页数据
     */
    IPage<DictTypeEntity> getPageList(long current, long pageSize);

    /**
     * 根据字典类型编码获取字典类型ID
     *
     * @param dictTypeCode 字典类型编码
     * @return String 字典类型ID
     */
    String getIdByDictTypeCode(String dictTypeCode);
}
