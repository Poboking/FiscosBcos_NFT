package org.knight.app.biz.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.knight.app.biz.account.dto.account.member.AccountRespDTO;
import org.knight.app.biz.account.dto.invite.InviteInfoRespDTO;
import org.knight.app.biz.account.dto.invite.InviteeRecordRespDTO;
import org.knight.app.biz.account.dto.member.MemberBindRealNameReqDTO;
import org.knight.app.biz.account.dto.member.MemberStatisticDataRespDTO;
import org.knight.app.biz.account.dto.member.MemberUpdateAvatarReqDTO;
import org.knight.app.biz.account.dto.member.MemberUpdateNickNameReqDTO;
import org.knight.app.biz.convert.account.MemberConvert;
import org.knight.app.biz.exception.BizException;
import org.knight.infrastructure.common.CipherTextUtil;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.NicknameUtils;
import org.knight.infrastructure.dao.domain.MemberEntity;
import org.knight.infrastructure.fisco.service.BlockAddressService;
import org.knight.infrastructure.repository.impl.MemberRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 21:39
 */
@Service
public class MemberService {
    private final MemberRepositoryImpl memberRepository;

    private final BlockAddressService blockAddressService;

    @Autowired
    public MemberService(MemberRepositoryImpl memberRepository, BlockAddressService blockAddressService) {
        this.memberRepository = memberRepository;
        this.blockAddressService = blockAddressService;
    }

    public AccountRespDTO getAccountInfo(String memberId) {
        MemberEntity entity = memberRepository.getAccountInfo(memberId);
        if (entity == null) {
            throw new BizException(memberId + ": getAccountInfo fail as a result of entity is null");
        }
        if (Boolean.TRUE.equals(entity.getDeletedFlag())) {
            throw new BizException(memberId + ": getAccountInfo fail as a result of entity is deleted");
        }
        if (entity.getAvatar() == null) {
            entity.setAvatar(NftConstants.默认头像);
        }
        return MemberConvert.INSTANCE.convert(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean bindReadName(MemberBindRealNameReqDTO reqDto, String memberId) {
        if (!memberRepository.bindReadName(reqDto.getRealName(), reqDto.getSsn(), reqDto.getMobile(), memberId)) {
            throw new BizException(memberId + ": bindReadName fail as a result of db update fail");
        }
        String blockAddress = blockAddressService.getBlockAddress();
        if (blockAddress == null || blockAddress.isEmpty()) {
            throw new BizException(memberId + ": bindReadName fail as a result of blockAddress is null");
        }
        if (!memberRepository.updateBlockChainAddr(blockAddress, memberId)) {
            throw new BizException(memberId + ": bindReadName fail as a result of db update fail");
        }
        return true;
    }

    public boolean updateNickName(MemberUpdateNickNameReqDTO reqDto, String memberId) {
        return memberRepository.updateNickName(memberId, reqDto.getNickName());
    }

    public boolean updateAvatar(MemberUpdateAvatarReqDTO reqDto, String memberId) {
        return memberRepository.update(new UpdateWrapper<MemberEntity>()
                .eq(Optional.ofNullable(memberId).isPresent(), "id", memberId)
                .set(Optional.ofNullable(reqDto.getAvatar()).isPresent(), "avatar", reqDto.getAvatar()));
    }

    public InviteInfoRespDTO getInviteInfo(String memberId) {
        MemberEntity entity = memberRepository.getById(memberId);
        return InviteInfoRespDTO.create(entity.getInviteCode());
    }

    public List<InviteeRecordRespDTO> getInviteeRecord(String memberId) {
        List<InviteeRecordRespDTO> result = new ArrayList<>();
        memberRepository.list(new UpdateWrapper<MemberEntity>()
                        .eq(Optional.ofNullable(memberId).isPresent(), "inviter_id", memberId))
                // TODO: 2024/3/17 21:39 以下方法待实现: inviteSuccessFlag & boughtFlag的判断逻辑
                .forEach(item -> result.add(InviteeRecordRespDTO.create(item.getMobile(), false, false)));
        return result;
    }

    public Double getBlance(String memberId) {
        return memberRepository.getById(memberId).getBalance();
    }

    public boolean checkMobileExist(String mobile) {
        return memberRepository.getOneOpt(new UpdateWrapper<MemberEntity>()
                        .eq(Optional.ofNullable(mobile).isPresent(), "mobile", mobile))
                .isPresent();
    }

    public boolean registerUser(String mobile) {
        return memberRepository.save(MemberEntity.quickInit(NicknameUtils.generate(), mobile));
    }

    public boolean registerUser(String mobile, String password, String inviterId) {
        return memberRepository.save(MemberEntity.init(Optional.ofNullable(inviterId).isPresent(),
                NicknameUtils.generate(), mobile, CipherTextUtil.sha256(password), inviterId));
    }

    public boolean LoginByCheckPwd(String mobile, String password) {
        return memberRepository.getOneOpt(new UpdateWrapper<MemberEntity>()
                        .eq(Optional.ofNullable(mobile).isPresent(), "mobile", mobile)
                        .eq(Optional.ofNullable(password).isPresent(), "login_pwd", CipherTextUtil.sha256(password)))
                .isPresent();
    }

    public String getIdByMobile(String mobile) {
        return memberRepository.getOneOpt(new UpdateWrapper<MemberEntity>()
                        .eq(Optional.ofNullable(mobile).isPresent(), "mobile", mobile))
                .map(MemberEntity::getId).orElse("-1");
    }

    public MemberStatisticDataRespDTO getMemberStatisticData() {
        String now = LocalDateTime.now().format(NftConstants.SIMPLE_DATE_FORMAT);
        LocalDate nowTime = LocalDate.parse(now, NftConstants.SIMPLE_DATE_FORMAT);
        return MemberStatisticDataRespDTO.builder()
                .accountCount(memberRepository.count(new QueryWrapper<MemberEntity>()
                        .isNull("deleted_flag")
                        .or(wrapper -> wrapper.eq("deleted_flag", false))))
                .realNameCount(memberRepository.count(new QueryWrapper<MemberEntity>()
                        .and(wrapper -> wrapper.isNull("deleted_flag").or().eq("deleted_flag", false))
                        .isNotNull("real_name")))
                .todayRegisterCount(memberRepository.count(new QueryWrapper<MemberEntity>()
                        .and(wrapper -> wrapper.isNull("deleted_flag").or().eq("deleted_flag", false))
                        .ge("registered_time", nowTime)
                        .lt("registered_time", nowTime.plusDays(1))))
                .build();
    }

    public boolean updateLatelyLoginTime(String loginId) {
        String nowString = LocalDateTime.now().format(NftConstants.DATE_FORMAT);
        Timestamp now = Timestamp.valueOf(nowString);
        return memberRepository.update(new UpdateWrapper<MemberEntity>()
                .eq(Optional.ofNullable(loginId).isPresent(), "id", loginId)
                .set("lately_login_time", now));
    }
}
