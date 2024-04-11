package org.knight.app.biz.dict;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.knight.app.biz.convert.dict.DictItemConvert;
import org.knight.app.biz.dict.dto.DictItemRespDTo;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.DictItemEntity;
import org.knight.infrastructure.dao.domain.DictTypeEntity;
import org.knight.infrastructure.repository.impl.DictItemRepositoryImpl;
import org.knight.infrastructure.repository.impl.DictTypeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 18:06
 */
@Service
@AllArgsConstructor
@SuppressWarnings("all")
public class DictService {
    @Autowired
    private DictItemRepositoryImpl dictItemRepository;
    @Autowired
    private DictTypeRepositoryImpl dictTypeRepository;

    public PageResult<DictTypeEntity> getDictTypePageList(long current, long pageSize) {
        return PageResult.convertFor(dictTypeRepository.getPageList(current, pageSize), pageSize);
    }

    public PageResult<DictItemRespDTo> getDictItemPageList(long current, long pageSize) {
        PageInfo<DictItemEntity> pageList = dictItemRepository.getPageList(current, pageSize);
        List<DictItemRespDTo> recordList = new ArrayList<>();
        pageList.getList().forEach(item -> {
            recordList.add(DictItemConvert.INSTANCE.convert(item));
        });
        return PageResult.convertFor(pageList, pageSize, recordList);
    }

    public PageResult<DictItemRespDTo> getDictItemPageListByDictTypeCode(long current, long pageSize, String dictTypeCode) {
        PageInfo<DictItemEntity> pageList = dictItemRepository.getPageListByDictTypeCode(current, pageSize, dictTypeCode);
        List<DictItemRespDTo> recordList = new ArrayList<>();
        pageList.getList().forEach(item -> {
            recordList.add(DictItemConvert.INSTANCE.convert(item));
        });
        return PageResult.convertFor(pageList, pageSize, recordList);
    }

    public List<DictItemRespDTo> getDictItemListByDictTypeCode(String dictTypeCode) {
        String id = dictTypeRepository.getIdByDictTypeCode(dictTypeCode);
        if (id == null) {
            return null;
        }
        List<DictItemEntity> list = dictItemRepository.getListByDictTypeId(id);
        List<DictItemRespDTo> recordList = new ArrayList<>();
        list.forEach(item -> {
            recordList.add(DictItemConvert.INSTANCE.convert(item));
        });
        return recordList;
    }
}
