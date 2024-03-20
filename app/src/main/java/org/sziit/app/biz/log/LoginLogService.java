package org.sziit.app.biz.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.convert.log.LoginLogConvert;
import org.sziit.app.biz.log.dto.loginlog.LoginLogReqDTO;
import org.sziit.app.biz.log.dto.loginlog.LoginLogRespDTO;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.LoginLogEntity;
import org.sziit.infrastructure.repository.impl.LoginLogRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/13 14:00
 */
@Service
@AllArgsConstructor
public class LoginLogService {
    @Autowired
    private LoginLogRepositoryImpl loginLogRepository;

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
        pageList.getRecords().forEach(item -> {
            recordList.add(LoginLogConvert.INSTANCE.convertToRespDto(item));
        });
        return PageResult.convertFor(pageList, pageSize, recordList);
    }

    /**
     * 分页查询 - 根据userName
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param userName       Log日志ID
     * @return PageResult<LoginLogRespDto> 分页数据
     */
    public PageResult<LoginLogRespDTO> getLoginLog(long current, long pageSize, String userName) {
        IPage<LoginLogEntity> pageList = loginLogRepository.getLoginLogByUserName(current, pageSize, userName);
        List<LoginLogRespDTO> recordList = new ArrayList<>();
        pageList.getRecords().forEach(item -> {
            recordList.add(LoginLogConvert.INSTANCE.convertToRespDto(item));
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
