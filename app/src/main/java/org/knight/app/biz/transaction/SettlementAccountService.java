package org.knight.app.biz.transaction;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.knight.app.biz.convert.transaction.SettlementAccountConvert;
import org.knight.app.biz.transaction.dto.settlement.SettlementAccountAddReqDTO;
import org.knight.app.biz.transaction.dto.settlement.SettlementAccountRespDTO;
import org.knight.infrastructure.dao.domain.SettlementAccountEntity;
import org.knight.infrastructure.repository.impl.SettlementAccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/20 22:05
 */
@Service
public class SettlementAccountService {

    private final SettlementAccountRepositoryImpl repository;

    @Autowired
    public SettlementAccountService(SettlementAccountRepositoryImpl repository) {
        this.repository = repository;
    }

    public List<SettlementAccountRespDTO> findAll(String loginId) {
        return SettlementAccountConvert.INSTANCE.convertToRespDTO(repository.list(new QueryWrapper<SettlementAccountEntity>()
                .eq(Optional.ofNullable(loginId).isPresent(), "member_id", loginId)));
    }


    public List<SettlementAccountRespDTO> findAll(String loginId, Boolean activated) {
        return SettlementAccountConvert.INSTANCE.convertToRespDTO(repository.list(new QueryWrapper<SettlementAccountEntity>()
                .eq(Optional.ofNullable(loginId).isPresent(), "member_id", loginId)
                .eq(Optional.ofNullable(activated).isPresent(), "activated", activated)));
    }


    public Boolean add(String loginId, SettlementAccountAddReqDTO reqDTO) {
        SettlementAccountEntity entity = SettlementAccountConvert.INSTANCE.convertToEntity(reqDTO);
        entity.setMemberId(loginId);
        return repository.save(entity);
    }

    public Boolean updateActivatedFlag(String loginId, String account, Boolean activated) {
        return repository.update(new UpdateWrapper<SettlementAccountEntity>()
                .eq(Optional.ofNullable(loginId).isPresent(), "member_id", loginId)
                .eq(Optional.ofNullable(account).isPresent(), "account", account)
                .set(Optional.ofNullable(activated).orElse(false), "activated", activated));
    }

}
