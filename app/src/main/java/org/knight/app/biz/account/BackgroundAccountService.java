package org.knight.app.biz.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.knight.app.biz.account.dto.account.background.BackgroundAccountRespDTO;
import org.knight.app.biz.convert.account.BackgroundAccountConvert;
import org.knight.infrastructure.common.CipherTextUtil;
import org.knight.infrastructure.dao.domain.BackgroundAccountEntity;
import org.knight.infrastructure.repository.impl.BackgroundAccountRepositoryImpl;

import java.util.Objects;
import java.util.Optional;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 0:36
 */
@Service
public class BackgroundAccountService {

    private final BackgroundAccountRepositoryImpl repository;

    @Autowired
    public BackgroundAccountService(BackgroundAccountRepositoryImpl backgroundAccountRepository) {
        this.repository = backgroundAccountRepository;
    }

    public Boolean checkExist(String userName) {
        return repository.exists(new QueryWrapper<BackgroundAccountEntity>()
                .eq(Optional.ofNullable(userName).isPresent(), "user_name", userName));
    }

    public Boolean checkPwd(String userName, String loginPwd) {
        return repository.getOne(new QueryWrapper<BackgroundAccountEntity>()
                .eq(Optional.ofNullable(userName).isPresent(), "user_name", userName)
                .eq(Optional.ofNullable(loginPwd).isPresent(), "login_pwd", CipherTextUtil.sha256(loginPwd))) != null;
    }

    public String getLoginId(String userName) {
        BackgroundAccountEntity entity = repository.getOne(new QueryWrapper<BackgroundAccountEntity>()
                .eq(Optional.ofNullable(userName).isPresent(), "user_name", userName));
        if (Objects.isNull(entity)) {
            return null;
        }
        return entity.getId();
    }

    public BackgroundAccountRespDTO getAccountInfo(String id) {
        BackgroundAccountEntity entity = repository.getOne(new QueryWrapper<BackgroundAccountEntity>()
                .eq(Optional.ofNullable(id).isPresent(), "id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        return BackgroundAccountConvert.INSTANCE.convertToRespDTO(entity);
    }
}
