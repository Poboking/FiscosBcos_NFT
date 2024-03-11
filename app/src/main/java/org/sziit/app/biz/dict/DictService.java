package org.sziit.app.biz.dict;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.convert.dict.DictItemConvert;
import org.sziit.app.biz.dict.dto.DictItemRespDto;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.DictItemEntity;
import org.sziit.infrastructure.dao.domain.DictTypeEntity;
import org.sziit.infrastructure.repository.impl.DictItemRepositoryImpl;
import org.sziit.infrastructure.repository.impl.DictTypeRepositoryImpl;

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

    public PageResult<DictItemRespDto> getDictItemPageList(long current, long pageSize) {
        IPage<DictItemEntity> pageList = dictItemRepository.getPageList(current, pageSize);
        List<DictItemRespDto> recordList = new ArrayList<>();
        pageList.getRecords().forEach(item -> {
            recordList.add(DictItemConvert.INSTANCE.convert(item));
        });
        return PageResult.convertFor(pageList, pageSize, recordList);
    }

    public PageResult<DictItemRespDto> getDictItemPageListByDictTypeCode(long current, long pageSize, String dictTypeCode) {
        IPage<DictItemEntity> pageList = dictItemRepository.getPageListByDictTypeCode(current, pageSize, dictTypeCode);
        List<DictItemRespDto> recordList = new ArrayList<>();
        pageList.getRecords().forEach(item -> {
            recordList.add(DictItemConvert.INSTANCE.convert(item));
        });
        return PageResult.convertFor(pageList, pageSize, recordList);
    }

    public List<DictItemRespDto> getDictItemListByDictTypeCode(String dictTypeCode) {
        List<DictItemEntity> list = dictItemRepository.getListByDictTypeCode(dictTypeCode);
        List<DictItemRespDto> recordList = new ArrayList<>();
        list.forEach(item -> {
            recordList.add(DictItemConvert.INSTANCE.convert(item));
        });
        return recordList;
    }
}
