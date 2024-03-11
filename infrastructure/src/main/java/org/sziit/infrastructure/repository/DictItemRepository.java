package org.sziit.infrastructure.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.sziit.infrastructure.dao.domain.DictItemEntity;

import java.util.List;

/**
 * @author poboking
 * @description 针对表【dict_item】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface DictItemRepository extends IService<DictItemEntity> {
    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<DictItemEntity> 分页数据
     */
    IPage<DictItemEntity> getPageList(long current, long pageSize);

    /**
     * 分页查询
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param dictTypeCode 字典类型编码
     * @return IPage<DictItemEntity> 分页数据
     */
    IPage<DictItemEntity> getPageListByDictTypeCode(long current, long pageSize, String dictTypeCode);

    /**
     * 根据字典类型编码获取字典项列表
     *
     * @param dictTypeCode 字典类型编码
     * @return List<DictItemEntity> 字典项列表
     */
    List<DictItemEntity> getListByDictTypeCode(String dictTypeCode);
}
