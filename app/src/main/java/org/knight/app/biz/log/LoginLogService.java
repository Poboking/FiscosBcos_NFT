package org.knight.app.biz.log;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.func.VoidFunc0;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.knight.app.biz.convert.log.LoginLogConvert;
import org.knight.app.biz.log.dto.loginlog.LoginLogReqDTO;
import org.knight.app.biz.log.dto.loginlog.LoginLogRespDTO;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.LoginLogEntity;
import org.knight.infrastructure.repository.impl.LoginLogRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/13 14:00
 */
@Service
public class LoginLogService {
    private final LoginLogRepositoryImpl loginLogRepository;

    private final MemberRepositoryImpl memberRepository;
    @Autowired
    public LoginLogService(LoginLogRepositoryImpl loginLogRepository, MemberRepositoryImpl memberRepository) {
        this.loginLogRepository = loginLogRepository;
        this.memberRepository = memberRepository;
    }


    /**
     * 保存登录日志
     *
     * @param repDto 登录日志请求DTO
     * @return boolean 是否保存成功
     */
    public boolean saveLoginLog(LoginLogReqDTO repDto) {
        return loginLogRepository.saveLoginLog(LoginLogConvert.INSTANCE.convertToEntity(repDto));
    }

    /**
     * 分页查询 - 全部
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageResult<LoginLogRespDto> 分页数据
     */
    public PageResult<LoginLogRespDTO> getLoginLog(long current, long pageSize) {
        IPage<LoginLogEntity> pageList = loginLogRepository.getPageList(current, pageSize);
        List<LoginLogRespDTO> recordList = new ArrayList<>();
        pageList.getRecords().forEach(item -> recordList.add(LoginLogConvert.INSTANCE.convertToRespDto(item)));
        return PageResult.convertFor(pageList, pageSize, recordList);
    }

    /**
     * 分页查询 - 根据memberId
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 用户ID
     * @return PageResult<LoginLogRespDto> 分页数据
     */
    public PageResult<LoginLogRespDTO> getLoginLog(long current, long pageSize, String memberId) {
        String mobile = memberRepository.getMobileByMemberId(memberId);
        IPage<LoginLogEntity> pageList = loginLogRepository.getLoginLogByMoblie(current, pageSize, mobile);
        List<LoginLogRespDTO> recordList = new ArrayList<>();
        pageList.getRecords().forEach(item -> {
            LoginLogRespDTO respDTO = LoginLogConvert.INSTANCE.convertToRespDto(item);
            respDTO.setLoginTime(DateUtil.format(item.getLoginTime(), NftConstants.DATE_FORMAT));
            if (Objects.isNull(item.getLoginTime())){
                respDTO.setLoginTime("未知");
            }
            recordList.add(respDTO);
        });
        return PageResult.convertFor(pageList, pageSize, recordList);
    }

    /**
     * 删除登录日志
     *
     * @param userName 用户名
     * @return boolean 是否删除成功
     */
    public boolean deleteLoginLog(String userName) {
        return loginLogRepository.deleteLoginLogByUserName(userName);
    }

    /**
     * 更新登录日志
     *
     * @param repDto 登录日志请求DTO
     * @return boolean 是否更新成功
     */
    public boolean updateLoginLog(LoginLogReqDTO repDto) {
        return loginLogRepository.updateLoginLogByUserName(LoginLogConvert.INSTANCE.convertToEntity(repDto));
    }
}
