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
     * 获取用户手机号
     *
     * @param memberId 用户ID
     * @return 用户手机号
     */
    String getMobileByMemberId(String memberId);

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

    /**
     * 获取用户ID - 根据用户手机号
     *
     * @param memberMobile 用户手机号
     * @return 用户ID
     */
    String getIdByMobile(String memberMobile);

    /**
     * 检查用户是否存在
     *
     * @param memberId 用户ID
     * @return 是否存在
     */
    Boolean checkExist(String memberId);

    /**
     * 获取用户昵称
     *
     * @param memberId 用户ID
     * @return 用户昵称
     */
    String getNameByMemberId(String memberId);

    /**
     * 检查用户是否实名认证
     *
     * @param memberId 用户ID
     * @return boolean
     */
    boolean checkRealName(String memberId);
}
