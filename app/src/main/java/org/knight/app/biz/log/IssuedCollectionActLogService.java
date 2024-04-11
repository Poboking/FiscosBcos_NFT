package org.knight.app.biz.log;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.knight.app.biz.convert.log.IssuedCollectionActionLogConvert;
import org.knight.app.biz.log.dto.collectionlog.IssuedCollectionActionLogRespDTO;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.IssuedCollectionActionLogEntity;
import org.knight.infrastructure.repository.impl.IssuedCollectionActionLogRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/28 11:12
 */
@Service
public class IssuedCollectionActLogService {
    private final IssuedCollectionActionLogRepositoryImpl actionLogRepository;

    private final MemberRepositoryImpl memberRepository;
    @Autowired
    public IssuedCollectionActLogService(IssuedCollectionActionLogRepositoryImpl actionLogRepository, MemberRepositoryImpl memberRepository) {
        this.actionLogRepository = actionLogRepository;
        this.memberRepository = memberRepository;
    }


    public List<IssuedCollectionActionLogRespDTO> findIssuedCollectionActionLog(String issuedCollectionId) {
        if (CharSequenceUtil.isBlank(issuedCollectionId)){
            return null;
        }
        List<IssuedCollectionActionLogEntity> entityList = actionLogRepository.list(new QueryWrapper<IssuedCollectionActionLogEntity>()
                .eq("issued_collection_id", issuedCollectionId));
        if (entityList.isEmpty()){
            return null;
        }
        List<IssuedCollectionActionLogRespDTO> respDTOs = new ArrayList<>();
        entityList.forEach(entity ->{
            IssuedCollectionActionLogRespDTO respDTO = IssuedCollectionActionLogConvert.INSTANCE.convertToRespDTO(entity);
            respDTO.setActionTime(entity.getActionTime().toLocalDateTime().format(NftConstants.DATE_FORMAT));
            respDTO.setMemberNickName(Optional.ofNullable(memberRepository.getNameByMemberId(entity.getMemberId())).orElse("DataError: Unknown User"));
            respDTOs.add(respDTO);
        });
        return respDTOs;
    }
}
