package org.sziit.app.biz.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.account.dto.account.member.AccountRespDTO;
import org.sziit.app.biz.account.dto.invite.InviteInfoRespDTO;
import org.sziit.app.biz.account.dto.invite.InviteeRecordRespDTO;
import org.sziit.app.biz.account.dto.member.MemberBindRealNameReqDTO;
import org.sziit.app.biz.account.dto.member.MemberStatisticDataRespDTO;
import org.sziit.app.biz.account.dto.member.MemberUpdateAvatarReqDTO;
import org.sziit.app.biz.account.dto.member.MemberUpdateNickNameReqDTO;
import org.sziit.app.biz.convert.account.MemberConvert;
import org.sziit.infrastructure.common.CipherTextUtil;
import org.sziit.infrastructure.common.NftConstants;
import org.sziit.infrastructure.common.NicknameUtils;
import org.sziit.infrastructure.dao.domain.MemberEntity;
import org.sziit.infrastructure.repository.impl.MemberRepositoryImpl;

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

    @Autowired
    public MemberService(MemberRepositoryImpl memberRepository) {
        this.memberRepository = memberRepository;
    }

    public AccountRespDTO getAccountInfo(String memberId) {
        return MemberConvert.INSTANCE.convert(memberRepository.getAccountInfo(memberId));
    }

    public boolean bindReadName(MemberBindRealNameReqDTO reqDto, String memberId) {
        return memberRepository.bindReadName(reqDto.getRealName(), reqDto.getSsn(), reqDto.getMobile(), memberId);
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
}
