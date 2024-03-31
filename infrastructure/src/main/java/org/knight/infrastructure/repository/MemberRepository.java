package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import org.knight.infrastructure.dao.domain.MemberEntity;

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


    /**
     * 用户实名认证
     *
     * @param realName 真实姓名
     * @param ssn      身份证号
     * @param mobile   手机号
     * @return 是否更新成功
     */
    boolean bindReadName(String realName, String ssn, String mobile, String memberId);

    /**
     * 减少用户余额
     *
     * @param memberId 用户ID
     * @param amount   金额
     * @return 是否更新成功
     */
    boolean reduceBalance(String memberId, Double amount);

    /**
     * 更新区块链地址
     *
     * @param memberId     用户ID
     * @param blockAddress 区块链地址
     * @return 是否更新成功
     */
    boolean updateBlockChainAddr(String blockAddress, String memberId);
}
