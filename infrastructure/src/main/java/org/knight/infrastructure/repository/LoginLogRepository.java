package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.dao.domain.LoginLogEntity;

/**
 * @author poboking
 * @description 针对表【login_log】的数据库操作Service
 * @createDate 2024-03-07 17:31:42
 */
public interface LoginLogRepository extends IService<LoginLogEntity> {
    /**
     * 分页查询 - 全部
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<LoginLogEntity> 分页数据
     */
    PageInfo<LoginLogEntity> getPageList(long current, long pageSize);

    /**
     * 分页查询 - 根据ID
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param id       Log日志ID
     * @return PageInfo<LoginLogEntity> 分页数据
     */
    PageInfo<LoginLogEntity> getPageListById(long current, long pageSize, String id);

    /**
     * 分页查询 - 根据用户名
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param userName 用户名
     * @return PageInfo<LoginLogEntity> 分页数据
     */
    PageInfo<LoginLogEntity> getLoginLogByMoblie(long current, long pageSize, String userName);

    /**
     * 保存登录日志
     *
     * @param loginLogEntity 登录日志实体
     * @return boolean 是否保存成功
     */
    boolean saveLoginLog(LoginLogEntity loginLogEntity);

    /**
     * 删除登录日志
     *
     * @param userName 登录日志实体
     * @return boolean 是否删除成功
     */
    boolean deleteLoginLogByUserName(String userName);

    /**
     * 更新登录日志
     *
     * @param loginLogEntity 登录日志实体
     * @return boolean 是否更新成功
     */
    boolean updateLoginLogByUserName(LoginLogEntity loginLogEntity);

    /**
     * 更新登录日志
     *
     * @param loginLogEntity 登录日志实体
     * @return boolean 是否更新成功
     */
    boolean updateLoginLogById(LoginLogEntity loginLogEntity);
}
