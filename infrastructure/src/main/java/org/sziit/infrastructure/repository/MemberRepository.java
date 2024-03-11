package org.sziit.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import org.sziit.infrastructure.dao.domain.MemberEntity;

/**
 * @author poboking
 * @description 针对表【member】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface MemberRepository extends IService<MemberEntity> {

    /**
     * 获取用户账户信息
     *
     * @param memberId 用户ID
     * @return 用户账户信息
     */
    public MemberEntity getAccountInfo(String memberId);

    /**
     * 更新用户昵称
     *
     * @param memberId 用户ID
     * @param nickName 昵称
     * @return 是否更新成功
     */
    public boolean updateNickName(String memberId, String nickName);
}
