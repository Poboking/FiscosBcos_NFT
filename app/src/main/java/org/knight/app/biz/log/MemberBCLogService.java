package org.knight.app.biz.log;

import cn.hutool.core.text.CharSequenceUtil;
import com.github.pagehelper.PageInfo;
import org.knight.app.biz.convert.log.MemberBCLogConvert;
import org.knight.app.biz.log.dto.balance.MemberBCLogRespDTO;
import org.knight.infrastructure.common.NftConstants;
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
public class MemberBCLogService {
    private final MemberBalanceChangeLogRepositoryImpl repository;

    @Autowired
    public MemberBCLogService(MemberBalanceChangeLogRepositoryImpl repository) {
        this.repository = repository;
    }

    public PageResult<MemberBCLogRespDTO> getMemberBCLog(long current, long size, String changeType) {
        PageInfo<MemberBalanceChangeLogEntity> pageList = null;
        if (changeType == null) {
            pageList = repository.getMemberBCLogPageList(current, size);
        } else {
            pageList = repository.getMemberBCLogPageList(current, size, changeType);
        }
        List<MemberBCLogRespDTO> recordList = new ArrayList<>();
        pageList.getList().forEach(item -> recordList.add(MemberBCLogConvert.INSTANCE.convertToRespDto(item)));
        return PageResult.convertFor(pageList, size, recordList);
    }

    public PageResult<MemberBCLogRespDTO> getMemberBCLog(long current, long size, String changeType, String memberId) {
        PageInfo<MemberBalanceChangeLogEntity> pageList = null;
        if (CharSequenceUtil.isBlank(changeType)) {
            pageList = repository.getMemberBCLogPageListByMemberId(current, size, memberId);
        } else {
            pageList = repository.getMemberBCLogPageListByMemberId(current, size, changeType, memberId);
        }
        List<MemberBCLogRespDTO> recordList = new ArrayList<>();
        pageList.getList().forEach(item -> {
            MemberBCLogRespDTO respDTO = MemberBCLogConvert.INSTANCE.convertToRespDto(item);
            if (respDTO.getChangeType().equals(NftConstants.会员余额变动日志类型_系统)){
                respDTO.setChangeTypeName("系统");
            } else if (respDTO.getChangeType().equals(NftConstants.会员余额变动日志类型_购买藏品)) {
                respDTO.setChangeTypeName("购买藏品");
            } else if (respDTO.getChangeType().equals(NftConstants.会员余额变动日志类型_购买转售的藏品)) {
                respDTO.setChangeTypeName("购买转售的藏品");
            } else if (respDTO.getChangeType().equals(NftConstants.会员余额变动日志类型_出售藏品)) {
                respDTO.setChangeTypeName("出售藏品");
            } else if (respDTO.getChangeType().equals(NftConstants.会员余额变动日志类型_提现)) {
                respDTO.setChangeTypeName("提现");
            } else if (respDTO.getChangeType().equals(NftConstants.会员余额变动日志类型_提现驳回)) {
                respDTO.setChangeTypeName("提现驳回");
            }else {
                respDTO.setChangeTypeName("未知");
            }
            recordList.add(respDTO);
        });
        return PageResult.convertFor(pageList, size, recordList);
    }
}
