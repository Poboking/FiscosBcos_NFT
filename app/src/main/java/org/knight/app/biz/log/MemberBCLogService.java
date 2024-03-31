package org.knight.app.biz.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.knight.app.biz.convert.log.MemberBCLogConvert;
import org.knight.app.biz.log.dto.balance.MemberBCLogRespDTO;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.MemberBalanceChangeLogEntity;
import org.knight.infrastructure.repository.impl.MemberBalanceChangeLogRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 23:06
 */
@Service
@AllArgsConstructor
public class MemberBCLogService {
    @Autowired
    private MemberBalanceChangeLogRepositoryImpl repository;

    public PageResult<MemberBCLogRespDTO> getMemberBCLog(long current, long size, String changeType) {
        IPage<MemberBalanceChangeLogEntity> pageList = null;
        if (changeType == null) {
            pageList = repository.getMemberBCLogPageList(current, size);
        } else {
            pageList = repository.getMemberBCLogPageList(current, size, changeType);
        }
        List<MemberBCLogRespDTO> recordList = new ArrayList<>();
        pageList.getRecords().forEach(item -> recordList.add(MemberBCLogConvert.INSTANCE.convertToRespDto(item)));
        return PageResult.convertFor(pageList, size, recordList);
    }

    public PageResult<MemberBCLogRespDTO> getMemberBCLog(long current, long size, String changeType, String memberId) {
        IPage<MemberBalanceChangeLogEntity> pageList = null;
        if (changeType == null) {
            pageList = repository.getMemberBCLogPageListByMemberId(current, size, memberId);
        } else {
            pageList = repository.getMemberBCLogPageListByMemberId(current, size, changeType, memberId);
        }
        List<MemberBCLogRespDTO> recordList = new ArrayList<>();
        pageList.getRecords().forEach(item -> recordList.add(MemberBCLogConvert.INSTANCE.convertToRespDto(item)));
        return PageResult.convertFor(pageList, size, recordList);
    }
}
