package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.MemberEntity;
import org.knight.infrastructure.dao.mapper.MemberMapper;
import org.knight.infrastructure.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author poboking
 * @description 针对表【member】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class MemberRepositoryImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberRepository {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberRepositoryImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    /**
     * 获取用户手机号
     *
     * @param memberId 用户ID
     * @return 用户手机号
     */
    @Override
    public String getMobileByMemberId(String memberId) {
        MemberEntity entity = memberMapper.selectById(memberId);
        if (Objects.isNull(entity) || CharSequenceUtil.isBlank(entity.getMobile())) {
            return null;
        }
        return entity.getMobile();
    }

    /**
     * 获取用户账户信息
     *
     * @param memberId 用户ID
     * @return MemberEntity 账号实体
     */
    @Override
    public MemberEntity getAccountInfo(String memberId) {
        return memberMapper.selectById(memberId);
    }

    /**
     * 更新用户昵称
     *
     * @param memberId 用户ID
     * @param nickName 昵称
     * @return 是否更新成功
     */
    @Override
    public boolean updateNickName(String memberId, String nickName) {
        return memberMapper.update(null,
                new UpdateWrapper<MemberEntity>().
                        eq("id", memberId).set("nick_name", nickName)) > 0;
    }

    /**
     * 用户实名认证
     *
     * @param realName 真实姓名
     * @param ssn      身份证号
     * @param mobile   手机号
     * @return 是否更新成功
     */
    @Override
    public boolean bindReadName(String realName, String ssn, String mobile, String memberId) {
        return memberMapper.update(new UpdateWrapper<MemberEntity>()
                .eq(!memberId.isEmpty(), "id", memberId)
                .eq(!mobile.isEmpty(), "mobile", mobile)
                .set(!CharSequenceUtil.isBlank(realName), "real_name", realName)
                .set(!CharSequenceUtil.isBlank(ssn), "identity_card", ssn)
                .set("bind_real_name_time", Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)))) > 0;
    }

    /**
     * 减少用户余额
     *
     * @param memberId 用户ID
     * @param amount   金额
     * @return 是否更新成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean reduceBalance(String memberId, Double amount) {
        if (amount < 0) {
            return false;
        }
        if (Boolean.FALSE.equals(checkExist(memberId))) {
            return false;
        }
        if (memberMapper.selectById(memberId).getBalance() < amount) {
            return false;
        }
        return memberMapper.update(null,
                new UpdateWrapper<MemberEntity>()
                        .eq("id", memberId)
                        .setSql("balance = balance - " + amount)) > 0;
    }

    /**
     * 赠加用户余额
     *
     * @param memberId 用户ID
     * @param amount   金额
     * @return 是否更新成功
     */
    @Override
    public boolean increaseBalance(String memberId, Double amount) {
        if (amount < 0) {
            return false;
        }
        if (Boolean.FALSE.equals(checkExist(memberId))) {
            return false;
        }
        return memberMapper.update(null,
                new UpdateWrapper<MemberEntity>()
                        .eq("id", memberId)
                        .setSql("balance = balance + " + amount)) > 0;
    }

    /**
     * 更新区块链地址
     *
     * @param blockAddress 区块链地址
     * @param memberId     用户ID
     * @return 是否更新成功
     */
    @Override
    public boolean updateBlockChainAddr(String blockAddress, String memberId) {
        String nowString = LocalDateTime.now().format(NftConstants.DATE_FORMAT);
        Timestamp now = Timestamp.valueOf(nowString);
        return memberMapper.update(null,
                new UpdateWrapper<MemberEntity>()
                        .eq("id", memberId)
                        .set("block_chain_addr", blockAddress)
                        .set("sync_chain_time", now)) > 0;
    }

    /**
     * 获取用户ID - 根据用户手机号
     *
     * @param memberMobile 用户手机号
     * @return 用户ID
     */
    @Override
    public String getIdByMobile(String memberMobile) {
        MemberEntity entity = memberMapper.selectOne(new QueryWrapper<MemberEntity>()
                .eq(Optional.ofNullable(memberMobile).isPresent(), "mobile", memberMobile));
        if (Objects.isNull(entity)) {
            return null;
        }
        return entity.getId();
    }

    /**
     * 检查用户是否存在
     *
     * @param memberId 用户ID
     * @return 是否存在
     */
    @Override
    public Boolean checkExist(String memberId) {
        if (CharSequenceUtil.isBlank(memberId)) {
            return false;
        }
        return memberMapper.exists(new QueryWrapper<MemberEntity>().eq("id", memberId));
    }

    /**
     * 获取用户昵称
     *
     * @param memberId 用户ID
     * @return 用户昵称
     */
    @Override
    public String getNameByMemberId(String memberId) {
        MemberEntity entity = memberMapper.selectOne(new QueryWrapper<MemberEntity>()
                .eq("id", memberId));
        if (Objects.isNull(entity)) {
            return null;
        }
        return entity.getNickName();
    }

    /**
     * 检查用户是否实名认证
     *
     * @param memberId 用户ID
     * @return boolean
     */
    @Override
    public boolean checkRealName(String memberId) {
        if (CharSequenceUtil.isBlank(memberId)) {
            return false;
        }

        List<MemberEntity> entities = memberMapper.selectList(new QueryWrapper<MemberEntity>()
                .eq("id", memberId)
                .and(e -> e.ne("real_name", "").or().isNotNull("real_name"))
                .and(e -> e.ne("identity_card", "").or().isNotNull("identity_card")));
        return !entities.isEmpty(); // 如果列表不为空，则表示查询到了符合条件的记录
    }


    /**
     * 获取用户区块链地址
     *
     * @param memberId
     * @return string address
     */
    @Override
    public String getAddressById(String memberId) {
        return memberMapper.selectOne(new QueryWrapper<MemberEntity>()
                .eq("id", memberId)).getBlockChainAddr();
    }
}




